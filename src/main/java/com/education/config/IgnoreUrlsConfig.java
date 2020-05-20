package com.education.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dell
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "security.ignored")
public class IgnoreUrlsConfig {

    private List<String> urls = new ArrayList<>();

}
