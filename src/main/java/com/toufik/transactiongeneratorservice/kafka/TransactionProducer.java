package com.toufik.transactiongeneratorservice.kafka;

import com.toufik.transactiongeneratorservice.config.MT103Properties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final MT103Properties MT103Properties;

    public TransactionProducer(KafkaTemplate<String, String> kafkaTemplate, MT103Properties MT103Properties) {
        this.kafkaTemplate = kafkaTemplate;
        this.MT103Properties = MT103Properties;
    }

    public void sendTransaction(String message) {
        kafkaTemplate.send(MT103Properties.getTopic(), message);
    }
}