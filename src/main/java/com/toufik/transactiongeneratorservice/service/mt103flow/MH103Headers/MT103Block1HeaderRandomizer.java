package com.toufik.transactiongeneratorservice.service.mt103flow.MH103Headers;

import java.util.Random;

/**
 * Generates a randomized Block 1 header for a SWIFT MT103 message.
 * Format: {1:F01<SenderBIC><SessionNumber><SequenceNumber>}
 */
public class MT103Block1HeaderRandomizer {

    private static final String APPLICATION_ID = "F";
    private static final String SERVICE_ID = "01";
    private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Generates a full Block 1 header string in SWIFT MT103 format.
     *
     * @return A randomized SWIFT Block 1 string.
     */
    public static String generateBlock1Header() {
        String senderBIC = generateRandomBIC();
        String sessionNumber = String.format("%04d", new Random().nextInt(10000));      // 4-digit session number
        String sequenceNumber = String.format("%06d", new Random().nextInt(1000000));   // 6-digit sequence number

        return String.format("{1:%s%s%s%s%s}",
                APPLICATION_ID, SERVICE_ID, senderBIC, sessionNumber, sequenceNumber);
    }

    /**
     * Generates a random BIC (Bank Identifier Code) in 11-character format.
     *
     * @return A random valid-length BIC string.
     */
    private static String generateRandomBIC() {
        String bankCode = getRandomUppercaseLetters(4);
        String countryCode = getRandomUppercaseLetters(2);
        String locationCode = getRandomUppercaseLetters(2);
        String branchCode = getRandomUppercaseLetters(3);
        return bankCode + countryCode + locationCode + branchCode;
    }

    /**
     * Generates a random string of uppercase letters.
     *
     * @param length The number of characters to generate.
     * @return A random uppercase string of specified length.
     */
    private static String getRandomUppercaseLetters(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(CHARSET.charAt(rand.nextInt(CHARSET.length())));
        }
        return sb.toString();
    }
}
