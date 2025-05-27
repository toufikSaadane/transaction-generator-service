package com.toufik.transactiongeneratorservice.service;

import com.toufik.transactiongeneratorservice.kafka.TransactionProducer;
import com.toufik.transactiongeneratorservice.model.MT103Data;
import com.toufik.transactiongeneratorservice.service.iban.IbanGeneratorByCountry;
import com.toufik.transactiongeneratorservice.service.statementnumber.MT103Field28CGenerator;
import com.toufik.transactiongeneratorservice.service.transactionReference.MT103Field20Generator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class MT103ScheduledJob {

    private final SimpleDateFormat fileTimestampFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private final TransactionProducer transactionProducer;

    public MT103ScheduledJob(TransactionProducer transactionProducer) {
        this.transactionProducer = transactionProducer;
    }

    @Scheduled(fixedRate = 100)
    public void generateMT103File() {
        try {
            MT103Data data = MT103Data.builder()
                    .transactionReference(MT103Field20Generator.generateField20())
                    .accountNumber(IbanGeneratorByCountry.generateRandomIban())
                    .statementNumber(MT103Field28CGenerator.generateField28C())
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
            String content = MT103Generator.generateMT103Content(data);
            String timestamp = fileTimestampFormat.format(new Date());
            String fileName = timestamp + "_" + data.getAccountNumber() + ".sta";
            MT103Generator.writeToFile(content, fileName);
            log.info("MT103 file generated: {}", fileName);
            transactionProducer.sendTransaction(IbanGeneratorByCountry.generateRandomIban());
        } catch (IOException e) {
            log.error("Error generating file: {}", e.getMessage());
        }
    }
}