package com.education;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author dell
 */
@EnableCaching
@MapperScan("com.education.mapper")
@SpringBootApplication
public class EducationApplication {

  public static void main(String[] args) {
    SpringApplication.run(EducationApplication.class, args);
  }

}
