package io.zhiller.whitelist.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("zhiller.whitelist")
public class WhiteListProperties {
  private String users;
}
