package com.toufik.transactiongeneratorservice.service.iban;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class IbanGenerator {
    private static final Random RANDOM = new Random();

    public static String generateRandomDutchIban() {
        String bankCode = randomLetters(4);
        String accountNumber = randomDigits(10);
        String checkDigits = String.format("%02d", RANDOM.nextInt(100));
        return "NL" + checkDigits + bankCode + accountNumber;
    }

    private static String randomLetters(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char letter = (char) ('A' + RANDOM.nextInt(26));
            sb.append(letter);
        }
        return sb.toString();
    }

    private static String randomDigits(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }
}