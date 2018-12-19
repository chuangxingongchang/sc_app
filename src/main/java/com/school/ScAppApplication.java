package com.school;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.school.example", "com.school.mapper"})
public class ScAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScAppApplication.class, args);
    }

}

