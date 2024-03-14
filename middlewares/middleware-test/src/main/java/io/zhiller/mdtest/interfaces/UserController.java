package io.zhiller.mdtest.interfaces;

import io.zhiller.whitelist.annotation.JoinWhiteList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  private final Logger logger = LoggerFactory.getLogger(UserController.class);

  @JoinWhiteList(key = "userId", returnJson = "{\"code\":\"1111\"}")
  @GetMapping("/api/queryUserInfo")
  public UserInfo queryUserInfo(@RequestParam String userId) {
    logger.info("查询用户信息：{}", userId);
    return UserInfo.builder()
      .name("jack" + userId)
      .age(19)
      .address("不知道在哪里").build();
  }
}
