package com.toufik.transactiongeneratorservice.service;

import com.toufik.transactiongeneratorservice.model.Mt940Data;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mt940Generator {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyMMdd");
    private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * Generates MT940 content based on the provided data.
     *
     * @param data The Mt940Data object containing transaction details.
     * @return The generated MT940 content as a String.
     */
    public static String generateMt940Content(Mt940Data data) {
        StringBuilder sb = new StringBuilder();

        // Header Section
        appendField(sb, "20", data.getTransactionReference());
        appendField(sb, "25", data.getAccountNumber());
        appendField(sb, "28C", data.getStatementNumber());

        // Opening Balance
        appendField(sb, "60F", data.getOpeningBalance());

        // Transaction Details
        String transactionLine = String.format("%s%s%s%sNTRFNONREF//%s",
                DATE_FORMAT.format(data.getValueDate()),
                DATE_FORMAT.format(data.getEntryDate()),
                data.getDebitCredit(),
                formatAmount(data.getAmount()),
                TIMESTAMP_FORMAT.format(new Date()));
        appendField(sb, "61", transactionLine);

        // Narrative
        String narrative = String.format("/EREF/%s/NAME/%s/REMI/%s",
                data.getEref(), data.getMerchantName(), data.getRemittanceInfo());
        appendField(sb, "86", narrative);

        // Closing Balance
        appendField(sb, "62F", data.getClosingBalance());

        return sb.toString();
    }

    private static void appendField(StringBuilder sb, String fieldCode, String value) {
        sb.append(":").append(fieldCode).append(":").append(value).append("\n");
    }

    private static String formatAmount(String amount) {
        return amount.replace(".", ","); // Ensure proper decimal format
    }

    public static void writeToFile(String content, String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
        }
    }
}