package com.toufik.transactiongeneratorservice.service;

import com.toufik.transactiongeneratorservice.model.Mt940Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Slf4j
public class Mt940Generator {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyMMdd");
    private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public static String generateMt940Content(Mt940Data data) {
        StringBuilder sb = new StringBuilder();

        appendField(sb, "20", data.getTransactionReference());
        appendField(sb, "25", data.getAccountNumber());
        appendField(sb, "28C", data.getStatementNumber());
        appendField(sb, "60F", data.getOpeningBalance());

        String transactionLine = String.format("%s%s%s%sNTRFNONREF//%s",
                DATE_FORMAT.format(data.getValueDate()),
                DATE_FORMAT.format(data.getEntryDate()),
                data.getDebitCredit(),
                formatAmount(data.getAmount()),
                TIMESTAMP_FORMAT.format(new Date()));
        appendField(sb, "61", transactionLine);

        String narrative = String.format("/EREF/%s/NAME/%s/REMI/%s",
                data.getEref(), data.getMerchantName(), data.getRemittanceInfo());
        appendField(sb, "86", narrative);

        appendField(sb, "62F", data.getClosingBalance());

        return sb.toString();
    }

    private static void appendField(StringBuilder sb, String fieldCode, String value) {
        sb.append(":").append(fieldCode).append(":").append(value).append("\n");
    }

    private static String formatAmount(String amount) {
        return amount.replace(".", ",");
    }

    public static void writeToFile(String content, String filename) throws IOException {
        String directoryPath = "src/main/resources/mt940_files";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            log.info("Creating directory: {}", directoryPath);
            directory.mkdirs();
        }
        File file = new File(directory, filename);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
            log.info("File written to: {}", file.getAbsolutePath());
        }catch (IOException e){
            log.error("Error writing to file: {}", e.getMessage());
            throw e;
        }
    }
}