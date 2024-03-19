package io.zhiller.gpt.domain.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {
  /*选择默认密钥*/
  private static final String defaultBase64EncodedSecretKey = "B*B^";

  private static final SignatureAlgorithm defaultSignatureAlgorithm = SignatureAlgorithm.HS256;

  public JwtUtil() {
    this(defaultBase64EncodedSecretKey, defaultSignatureAlgorithm);
  }

  private final String base64EncodedSecretKey;
  private final SignatureAlgorithm signatureAlgorithm;

  public JwtUtil(String secretKey, SignatureAlgorithm signatureAlgorithm) {
    this.base64EncodedSecretKey = Base64.encodeBase64String(secretKey.getBytes());
    this.signatureAlgorithm = signatureAlgorithm;
  }

  public String encode(String issuer, long ttlMillis, Map<String, Object> claims) {
    // iss签发人，ttlMillis生存时间，claims是指还想要在jwt中存储的一些非隐私信息
    if (claims == null) {
      claims = new HashMap<>();
    }
    long nowMillis = System.currentTimeMillis();

    JwtBuilder builder = Jwts.builder()
      // 荷载部分
      .setClaims(claims)
      // 这个是JWT的唯一标识，一般设置成唯一的，这个方法可以生成唯一标识
      .setId(UUID.randomUUID().toString())//2.
      // 签发时间
      .setIssuedAt(new Date(nowMillis))
      // 签发人，也就是JWT是给谁的（逻辑上一般都是username或者userId）
      .setSubject(issuer)
      .signWith(signatureAlgorithm, base64EncodedSecretKey);//这个地方是生成jwt使用的算法和秘钥
    if (ttlMillis >= 0) {
      long expMillis = nowMillis + ttlMillis;
      Date exp = new Date(expMillis);//4. 过期时间，这个也是使用毫秒生成的，使用当前时间+前面传入的持续时间生成
      builder.setExpiration(exp);
    }
    return builder.compact();
  }

  public Claims decode(String jwtToken) {
    // 得到 DefaultJwtParser
    return Jwts.parser()
      // 设置签名的秘钥
      .setSigningKey(base64EncodedSecretKey)
      // 设置需要解析的 jwt
      .parseClaimsJws(jwtToken)
      .getBody();
  }

  public boolean isVerify(String jwtToken) {
    // 这个是官方的校验规则，这里只写了一个”校验算法“，可以自己加
    Algorithm algorithm = null;
    switch (signatureAlgorithm) {
      case HS256:
        algorithm = Algorithm.HMAC256(Base64.decodeBase64(base64EncodedSecretKey));
        break;
      default:
        throw new RuntimeException("不支持该算法");
    }
    JWTVerifier verifier = JWT.require(algorithm).build();
    verifier.verify(jwtToken);
    // 校验不通过会抛出异常
    // 判断合法的标准：1. 头部和荷载部分没有篡改过。2. 没有过期
    return true;
  }
}
