package com.toufik.transactiongeneratorservice.service.mt103flow.MH103Headers;

import java.util.Random;

/**
 * Generates a randomized Block 2 header for a SWIFT MT103 message.
 *
 * Format: {2:I103<ReceiverBIC><MessagePriority>}
 *
 * - I103 = Input message type MT103
 * - Receiver BIC = 11-character Bank Identifier Code (randomized)
 * - Message Priority = typically "N" (Normal), but could be U (Urgent), S (System)
 */
public class MT103Block2HeaderRandomizer {

    private static final String MESSAGE_TYPE = "I103";
    private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String[] PRIORITIES = {"N", "U", "S"};

    /**
     * Generates a full Block 2 header string in SWIFT MT103 format.
     * @return A randomized SWIFT Block 2 header string.
     */
    public static String generateBlock2Header() {
        String receiverBIC = generateRandomBIC();
        String messagePriority = PRIORITIES[new Random().nextInt(PRIORITIES.length)];
        return String.format("{2:%s%s%s}", MESSAGE_TYPE, receiverBIC, messagePriority);
    }

    private static String generateRandomBIC() {
        return getRandomUppercaseLetters(4) +  // Bank code
                getRandomUppercaseLetters(2) +  // Country code
                getRandomUppercaseLetters(2) +  // Location code
                getRandomUppercaseLetters(3);   // Branch code
    }

    private static String getRandomUppercaseLetters(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(CHARSET.charAt(rand.nextInt(CHARSET.length())));
        }
        return sb.toString();
    }
}
