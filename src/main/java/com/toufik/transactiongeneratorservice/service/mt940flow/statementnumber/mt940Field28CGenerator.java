package com.toufik.transactiongeneratorservice.service.mt940flow.statementnumber;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class mt940Field28CGenerator {

    private static final Random RANDOM = new Random();

    /**
     * Generates a:28C: field with:
     * - Statement number exactly 6 digits long, last digit is 1 or 2
     * - Sequence number either 001 or 002
     *
     * @return formatted :28C: string.
     */
    public static String generateField28C() {
        int firstFiveDigits = RANDOM.nextInt(100000); // 0 to 99999
        int lastDigit = RANDOM.nextBoolean() ? 1 : 2;
        int statementNumber = firstFiveDigits * 10 + lastDigit;
        int sequenceNumber = RANDOM.nextBoolean() ? 1 : 2;
        String formatted = String.format("%06d/%03d", statementNumber, sequenceNumber);
        log.info("Generated :28C: field: {}", formatted);
        return formatted;
    }
}