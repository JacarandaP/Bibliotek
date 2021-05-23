package com.bibliotek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BibliotekApplication {

    public static void main(String[] args) {

        System.out.println("hello");
        SpringApplication.run(BibliotekApplication.class, args);
    }

}
