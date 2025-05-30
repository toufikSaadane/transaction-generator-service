package com.toufik.transactiongeneratorservice.service.mt103flow.mt103parser;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class Mt103FileProcessor {

    private final Mt103Parser mt103Parser;

    public Mt103FileProcessor(Mt103Parser mt103Parser) {
        this.mt103Parser = mt103Parser;
    }

//    @Scheduled(fixedRate = 1000) // Process files every 5 seconds
    public String processMt103Files() throws IOException {
        return mt103Parser.parseMt103FilesToJsonString();
    }
}