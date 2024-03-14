package io.zhiller.mdtest.interfaces;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {
  private String code;
  private String info;

  private String name;
  private Integer age;
  private String address;
}
