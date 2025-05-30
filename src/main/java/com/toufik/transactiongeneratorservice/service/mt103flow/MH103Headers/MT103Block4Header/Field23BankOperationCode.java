package com.toufik.transactiongeneratorservice.service.mt103flow.MH103Headers.MT103Block4Header;

import java.util.Random;

public class Field23BankOperationCode {

    private static final String[] VALID_CODES = {
            "CRED", // Credit Transfer
            "SPAY", // Special Payment
            "SSTD", // Same-day Transfer
            "SPRI"  // Priority Payment
    };

    private static final Random RANDOM = new Random();

    /**
     * Randomly selects a valid bank operation code and appends
     * Field 23B to the provided StringBuilder in SWIFT format.
     */
    public static String appendBankOperationCode() {
        String selectedCode = VALID_CODES[RANDOM.nextInt(VALID_CODES.length)];
        return ":23B:" + selectedCode ;
    }

}
