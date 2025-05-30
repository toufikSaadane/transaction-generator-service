package com.toufik.transactiongeneratorservice.service.mt103flow;

import com.toufik.transactiongeneratorservice.kafka.TransactionProducer;
import com.toufik.transactiongeneratorservice.model.Mt103Data;
import com.toufik.transactiongeneratorservice.service.iban.IbanGeneratorByCountry;
import com.toufik.transactiongeneratorservice.service.mt103flow.mt103parser.Mt103FileProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Scheduled job that generates and writes MT103 messages every second.
 */
@Component
@Slf4j
public class Mt103ScheduledJob {

    private final TransactionProducer transactionProducer;
    private final SimpleDateFormat fileTimestampFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private final Mt103FileProcessor mt103FileProcessor;

    public Mt103ScheduledJob(TransactionProducer transactionProducer, Mt103FileProcessor mt103FileProcessor) {
        this.transactionProducer = transactionProducer;
        this.mt103FileProcessor = mt103FileProcessor;
    }

    @Scheduled(fixedRate = 1000)
    public void generateMt103File() {
        try {
            Mt103Data data = createMockData();
            String content = Mt103MessageBuilder.buildMessage(data);
            log.info("Generated MT103 content:\n{}", content);
            String timestamp = fileTimestampFormat.format(new Date());
            String filename = "MT103_" + timestamp + ".sta";

            Mt103MessageBuilder.writeToFile(content, filename);
            log.info("Generated MT103 file: {}", filename);

            transactionProducer.sendTransaction(mt103FileProcessor.processMt103Files());
        } catch (IOException e) {
            log.error("Error generating MT103 file: {}", e.getMessage(), e);
        }
    }

    private Mt103Data createMockData() {
        String reference = "REF" + fileTimestampFormat.format(new Date());

        return Mt103Data.builder()
                .senderBic("BANKBEBBAXXX")
                .messageType("103")
                .receiverBic("BANKDEFFXXXX")
                .userReference(reference)
                .transactionReference(reference)
                .bankOperationCode("CRED")
                .valueDate(new Date())
                .currency("EUR")
                .amount("12345,67")
                .originalAmount("12345,67")
                .orderingAccount(IbanGeneratorByCountry.generateRandomIbanAndCountry().getIban())
                .orderingName("JOHN DOE")
                .orderingAddress("1 MAIN STREET\nBRUSSELS BE")
                .orderingInstitution("")
                .senderCorrespondent("")
                .receiverCorrespondent("")
                .intermediary("")
                .accountWithInstitution("")
                .beneficiaryAccount(IbanGeneratorByCountry.generateRandomIbanAndCountry().getIban())
                .beneficiaryName("")
                .beneficiaryAddress("")
                .paymentDetails(" " + reference.substring(3))
                .charges("SHA")
                .senderToReceiverInfo("")
                .regulatoryReporting("")
                .checksum(generateChecksum())
                .build();
    }

    private String generateChecksum() {
        return String.format("%012X", System.currentTimeMillis());
    }
}
