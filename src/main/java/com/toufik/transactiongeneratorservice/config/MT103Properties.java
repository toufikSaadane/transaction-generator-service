package com.toufik.transactiongeneratorservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mt103")
@Data
public class MT103Properties {
    private String topic;
    private String outputDir;
}