package com.java.my.spring.security.myspringsecurity.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "myproperties")
@Configuration
@Data
public class MyConfigProperties {
    private int id;
    private String name;
    private int age;
}