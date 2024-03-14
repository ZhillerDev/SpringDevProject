package io.zhiller.mdtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"io.zhiller.mdtest.*"})
public class MiddlewareTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(MiddlewareTestApplication.class, args);
  }

}
