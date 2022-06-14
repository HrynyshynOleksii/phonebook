package com.example.phonebookweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan

public class PhoneBookWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneBookWebApplication.class, args);
    }

}


