package com.nhnacademy.springboot.test.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter
@ConfigurationProperties(prefix = "init.file")
public class InitFileProperties {
    private String name;
}
