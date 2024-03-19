package io.zhiller.gpt;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FirstGptApplication {

  private Logger logger = LoggerFactory.getLogger(FirstGptApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(FirstGptApplication.class, args);
  }

  @GetMapping("/verify")
  public ResponseEntity<String> verify(String token) {
    logger.debug("验证 token：{}", token);
    if ("success".equals(token)) {
      return ResponseEntity.status(HttpStatus.OK).build();
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @GetMapping("/success")
  public String success() {
    return "test success by xfg";
  }

}
