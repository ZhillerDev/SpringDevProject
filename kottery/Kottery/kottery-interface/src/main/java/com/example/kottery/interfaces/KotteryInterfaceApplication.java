package com.example.kottery.interfaces;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Configurable
@EnableDubbo
@MapperScan("io.zhiller.kottery.infrastructure.dao")
@ComponentScan("io.zhiller.kottery.domain")
@ComponentScan("io.zhiller.kottery.infrastructure")
public class KotteryInterfaceApplication {

  public static void main(String[] args) {
    SpringApplication.run(KotteryInterfaceApplication.class, args);
  }

}
