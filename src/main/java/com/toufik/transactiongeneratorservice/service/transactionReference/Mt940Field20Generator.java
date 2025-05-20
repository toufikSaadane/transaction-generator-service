package com.toufik.transactiongeneratorservice.service.transactionReference;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class Mt940Field20Generator {

    private static final Random RANDOM = new Random();

    /**
     * Generates a random :20: transaction reference for an MT940 statement.
     * The generated reference consists of a fixed prefix ("REF") and a random alphanumeric sequence.
     *
     * @return A randomly generated transaction reference, e.g., "REFA1B2C3D4".
     */
    public static String generateField20() {
        String randomPart = generateRandomAlphanumeric(9);
        log.info("Generated random transaction reference: {}", randomPart);
        return "REF" + randomPart;
    }

    private static String generateRandomAlphanumeric(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (RANDOM.nextBoolean()) {
                sb.append(RANDOM.nextInt(10));
            } else {
                char letter = (char) ('A' + RANDOM.nextInt(26));
                sb.append(letter);
            }
        }
        return sb.toString();
    }
}