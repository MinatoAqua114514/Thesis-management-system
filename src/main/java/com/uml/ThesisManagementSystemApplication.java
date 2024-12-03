package com.uml;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.uml.*.dao")
public class ThesisManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThesisManagementSystemApplication.class, args);
    }

}
