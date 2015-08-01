package com.smartbee.spring.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:./cfg/application.properties")
public class SpringBootTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTutorialApplication.class, args);
    }

}
