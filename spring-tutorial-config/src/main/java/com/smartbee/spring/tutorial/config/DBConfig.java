package com.smartbee.spring.tutorial.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by TheArtOfMovement on 6/29/15.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.smartbee.spring.tutorial.repositories")
@EntityScan(basePackages = "com.smartbee.spring.tutorial.domain")
public class DBConfig {
}
