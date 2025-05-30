package com.toufik.transactiongeneratorservice.kafka;

import com.toufik.transactiongeneratorservice.config.mt940Properties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final mt940Properties mt940Properties;

    public TransactionProducer(KafkaTemplate<String, String> kafkaTemplate, mt940Properties mt940Properties) {
        this.kafkaTemplate = kafkaTemplate;
        this.mt940Properties = mt940Properties;
    }

    public void sendTransaction(String message) {
        kafkaTemplate.send(mt940Properties.getTopic(), message);
    }
}