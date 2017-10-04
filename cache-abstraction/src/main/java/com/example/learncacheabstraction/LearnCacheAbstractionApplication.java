package com.example.learncacheabstraction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:cache-config.xml")
@EnableCaching
public class LearnCacheAbstractionApplication {

  public static void main(String[] args) {
    SpringApplication.run(LearnCacheAbstractionApplication.class, args);
  }

}
