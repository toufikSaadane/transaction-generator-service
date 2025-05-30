package com.toufik.transactiongeneratorservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mt940")
@Data
public class mt940Properties {
    private String topic;
    private String outputDir;
}