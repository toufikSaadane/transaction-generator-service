package com.toufik.transactiongeneratorservice.service.mt940flow;

import com.toufik.transactiongeneratorservice.kafka.TransactionProducer;
import com.toufik.transactiongeneratorservice.model.Mt940Data;
import com.toufik.transactiongeneratorservice.service.iban.IbanGeneratorByCountry;
import com.toufik.transactiongeneratorservice.service.mt940flow.statementnumber.mt940Field28CGenerator;
import com.toufik.transactiongeneratorservice.service.mt940flow.transactionReference.mt940Field20Generator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class mt940ScheduledJob {

    private final SimpleDateFormat fileTimestampFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private final TransactionProducer transactionProducer;

    public mt940ScheduledJob(TransactionProducer transactionProducer) {
        this.transactionProducer = transactionProducer;
    }

//    @Scheduled(fixedRate = 100)
    public void generatemt940File() {
        try {
            Mt940Data data = generateRandomMt940Data();
            String content = mt940Generator.generatemt940Content(data);
            String timestamp = fileTimestampFormat.format(new Date());
            String fileName = timestamp + "_" + data.getAccountNumber() + ".sta";
            mt940Generator.writeToFile(content, fileName);
            log.info("mt940 file generated: {}", fileName);
            transactionProducer.sendTransaction(IbanGeneratorByCountry.generateRandomIbanAndCountry().getIban());
        } catch (IOException e) {
            log.error("Error generating file: {}", e.getMessage());
        }
    }

    private Mt940Data generateRandomMt940Data() {
        return Mt940Data.builder()
                .transactionReference(mt940Field20Generator.generateField20())
                .accountNumber(IbanGeneratorByCountry.generateRandomIbanAndCountry().getIban())
                .statementNumber(mt940Field28CGenerator.generateField28C())
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
    }
}