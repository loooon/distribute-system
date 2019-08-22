package com.distribute;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableEurekaServer
@SpringBootApplication
@EnableZuulProxy
@EnableSwagger2
public class ZuulServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ZuulServiceApplication.class, args);
  }
}
