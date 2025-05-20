package com.toufik.transactiongeneratorservice.service;

import com.toufik.transactiongeneratorservice.kafka.TransactionProducer;
import com.toufik.transactiongeneratorservice.model.Mt940Data;
import com.toufik.transactiongeneratorservice.service.iban.IbanGeneratorByCountry;
import com.toufik.transactiongeneratorservice.service.transactionReference.Mt940Field20Generator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class Mt940ScheduledJob {

    private final SimpleDateFormat fileTimestampFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private final TransactionProducer transactionProducer;

    public Mt940ScheduledJob(TransactionProducer transactionProducer) {
        this.transactionProducer = transactionProducer;
    }

    @Scheduled(fixedRate = 1000)
    public void generateMt940File() {

        transactionProducer.sendTransaction("Message from Mt940ScheduledJob");
        try {
            Mt940Data data = Mt940Data.builder()
                    .transactionReference(Mt940Field20Generator.generateField20())
                    .accountNumber(IbanGeneratorByCountry.generateRandomIban())
                    .statementNumber("00001/001")
                    .openingBalance("C231025EUR1000,00")
                    .valueDate(new Date())
                    .entryDate(new Date())
                    .debitCredit("D")
                    .amount("150,00")
                    .closingBalance("C231025EUR850,00")
                    .eref("INVOICE-12345")
                    .merchantName("MERCHANT XYZ")
                    .remittanceInfo("PAYMENT FOR SERVICES")
                    .build();
            String content = Mt940Generator.generateMt940Content(data);
            String timestamp = fileTimestampFormat.format(new Date());
            String fileName = timestamp + "_" + data.getAccountNumber() + ".sta";
            Mt940Generator.writeToFile(content, fileName);
            log.info("MT940 file generated: {}", fileName);
            transactionProducer.sendTransaction(IbanGeneratorByCountry.generateRandomIban());
        } catch (IOException e) {
            log.error("Error generating file: {}", e.getMessage());
        }
    }
}