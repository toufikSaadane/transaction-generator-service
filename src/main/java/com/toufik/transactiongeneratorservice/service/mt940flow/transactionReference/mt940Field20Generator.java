package com.toufik.transactiongeneratorservice.service.mt940flow.transactionReference;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class mt940Field20Generator {

    private static final Random RANDOM = new Random();

    /**
     * Generates a random :20: transaction reference for a mt940 statement.
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
        return IntStream.range(0, length)
                .mapToObj(i -> RANDOM.nextBoolean()
                        ? String.valueOf(RANDOM.nextInt(10))
                        : String.valueOf((char)('A' + RANDOM.nextInt(26))))
                .collect(Collectors.joining());
    }
}