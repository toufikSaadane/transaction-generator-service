package com.toufik.transactiongeneratorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TransactionGeneratorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionGeneratorServiceApplication.class, args);
    }

}
