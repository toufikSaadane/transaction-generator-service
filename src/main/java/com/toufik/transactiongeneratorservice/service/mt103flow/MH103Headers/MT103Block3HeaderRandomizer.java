package com.toufik.transactiongeneratorservice.service.mt103flow.MH103Headers;


import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * Generates a randomized Block 3 (User Header Block) for a SWIFT MT103 message.
 *
 * Format: {3:{108:<UserReference>}}
 *
 * - 108 = Unique user reference identifier (randomized string)
 */
@Slf4j
public class MT103Block3HeaderRandomizer {

    private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * Generates a full Block 3 header string in SWIFT MT103 format.
     * @return A randomized SWIFT Block 3 string.
     */
    public static String generateBlock3Header() {
        String userReference = generateRandomReference(12);  // Typically max 16 characters
        return String.format("{3:{108:%s}}", userReference);
    }

    /**
     * Generates a random uppercase alphanumeric string.
     * @param length The desired string length.
     * @return Random reference string.
     */
    private static String generateRandomReference(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(CHARSET.charAt(rand.nextInt(CHARSET.length())));
        }
        log.info("Generated random user reference: {}", sb);
        return sb.toString();
    }
}
