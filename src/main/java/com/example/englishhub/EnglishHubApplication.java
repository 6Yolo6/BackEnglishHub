package com.example.englishhub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.englishhub.mapper")
public class EnglishHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnglishHubApplication.class, args);
    }

}
