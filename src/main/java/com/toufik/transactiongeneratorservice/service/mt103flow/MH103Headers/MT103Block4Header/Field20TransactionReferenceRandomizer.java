package com.toufik.transactiongeneratorservice.service.mt103flow.MH103Headers.MT103Block4Header;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Generates the :20: field for SWIFT MT103 messages.
 * Format: :20:<TransactionReference>
 *
 * The reference starts with a prefix "REF" followed by 9 random alphanumeric characters.
 * Example: :20:REFA1B2C3D4
 */
public class Field20TransactionReferenceRandomizer {

    private static final Random RANDOM = new Random();

    /**
     * Generates the full :20: field line.
     * @return a SWIFT-formatted :20: line with a random transaction reference.
     */
    public static String generateTransactionReference() {
        return ":20:" + generateReference();
    }

    /**
     * Generates the random reference portion (e.g. "REFA1B2C3D4").
     * @return A random transaction reference string.
     */
    private static String generateReference() {
        return "REF" + IntStream.range(0, 9)
                .mapToObj(i -> RANDOM.nextBoolean()
                        ? String.valueOf(RANDOM.nextInt(10))
                        : String.valueOf((char) ('A' + RANDOM.nextInt(26))))
                .collect(Collectors.joining());
    }
}
