package io.zhiller.gpt.domain.security.model.vo;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {
  private String jwt;

  public JwtToken(String jwt) {
    this.jwt = jwt;
  }

  @Override
  public Object getPrincipal() {
    return jwt;
  }

  @Override
  public Object getCredentials() {
    return jwt;
  }
}
