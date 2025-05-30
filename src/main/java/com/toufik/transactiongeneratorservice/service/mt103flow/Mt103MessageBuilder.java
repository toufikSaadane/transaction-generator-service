package com.toufik.transactiongeneratorservice.service.mt103flow;


import com.toufik.transactiongeneratorservice.model.IbanAndCountry;
import com.toufik.transactiongeneratorservice.model.Mt103Data;
import com.toufik.transactiongeneratorservice.service.iban.IbanGeneratorByCountry;
import com.toufik.transactiongeneratorservice.service.mt103flow.MH103Headers.MT103Block1HeaderRandomizer;
import com.toufik.transactiongeneratorservice.service.mt103flow.MH103Headers.MT103Block2HeaderRandomizer;
import com.toufik.transactiongeneratorservice.service.mt103flow.MH103Headers.MT103Block3HeaderRandomizer;
import com.toufik.transactiongeneratorservice.service.mt103flow.MH103Headers.MT103Block4Header.*;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Utility class to construct and write SWIFT MT103 messages.
 */
@Slf4j
public class Mt103MessageBuilder {


    /**
     * Builds the MT103 message content based on provided data.
     */
    public static String buildMessage(Mt103Data data) {
        StringBuilder sb = new StringBuilder();
        // Block 1 - Basic Header
        sb.append(MT103Block1HeaderRandomizer.generateBlock1Header()).append("\n");
        // Block 2 - Application Header
        sb.append(MT103Block2HeaderRandomizer.generateBlock2Header()).append("\n");
        // Block 3 - User Header
        sb.append(MT103Block3HeaderRandomizer.generateBlock3Header()).append("\n");
        // Block 4 - Text
        sb.append("{4:\n");
        sb.append(Field20TransactionReferenceRandomizer.generateTransactionReference()).append("\n");
        sb.append(Field23BankOperationCode.appendBankOperationCode()).append("\n");
        sb.append(Field32AValueDateCurrencyAmount.generateValueDateCurrencyAmount()).append("\n");
        sb.append(Field33BCurrencyAmount.generateCurrencyAmount()).append("\n");
        sb.append(Field50KOrderingCustomer.generateOrderingCustomer()).append("\n");

        IbanAndCountry ibanAndCountry = IbanGeneratorByCountry.generateRandomIbanAndCountry();
        log.info("Generated IBAN: {}", ibanAndCountry.getIban());
        log.info("Generated country: {}", ibanAndCountry.getCountry());
        log.info("Generated BIC: {}", Field52ABicGenerator.generateBic(ibanAndCountry.getCountry()));
        sb.append(Field52ABicGenerator.generateBic(ibanAndCountry.getCountry())).append("\n");
        appendField(sb, "53A", data.getSenderCorrespondent());
        appendField(sb, "54A", data.getReceiverCorrespondent());
        appendField(sb, "56A", data.getIntermediary());
        appendField(sb, "57A", data.getAccountWithInstitution());
        appendField(sb, "59", "/" + data.getBeneficiaryAccount() + "\n" + data.getBeneficiaryName() + "\n" + data.getBeneficiaryAddress());
        appendField(sb, "70", data.getPaymentDetails());
        appendField(sb, "71A", data.getCharges());
        appendField(sb, "72", data.getSenderToReceiverInfo());
        if (data.getRegulatoryReporting() != null) {
            appendField(sb, "77B", data.getRegulatoryReporting());
        }
        sb.append("-}\n");

        // Block 5 - Trailer
        sb.append("{5:{CHK:").append(data.getChecksum()).append("}}");

        return sb.toString();
    }

    private static void appendField(StringBuilder sb, String fieldCode, String value) {
        sb.append(":").append(fieldCode).append(":").append(value).append("\n");
    }

    private static String formatAmount(String amount) {
        return amount.replace(".", ",");
    }

    /**
     * Writes the message content to a file in the MT103 resource folder.
     */
    public static void writeToFile(String content, String filename) throws IOException {
        String directoryPath = "src/main/resources/mt103_files";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directory, filename);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
            log.info("MT103 file written to: {}", file.getAbsolutePath());
        }
    }
}
