package com.toufik.transactiongeneratorservice.service.mt103flow.MH103Headers.MT103Block4Header;

import com.toufik.transactiongeneratorservice.util.EUCountryCode;

import java.util.Random;

/**
 * Generates a random SWIFT MT103 Field 52A BIC string.
 *
 * <p>Field 52A (Ordering Institution) specifies the BIC of the ordering institution.</p>
 *
 * <p>Example output:</p>
 * <pre>
 * :52A:DEUTDEFFXXX
 * </pre>
 */
public class Field52ABicGenerator {

    private static final String BANK_CODE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOCATION_BRANCH_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new Random();

    public static String generateBic(EUCountryCode country) {
        String bankCode = randomString(BANK_CODE_CHARS, 4);
        String countryCode = country.getIbanCountryCode();
        String locationCode = randomString(LOCATION_BRANCH_CHARS, 2);
        String branchCode = randomString(LOCATION_BRANCH_CHARS, 3);
        return ":52A:" + bankCode + countryCode + locationCode + branchCode;
    }

    private static String randomString(String chars, int length) {
        StringBuilder sb = new StringBuilder(length);
        for(int i = 0; i < length; i++) {
            sb.append(chars.charAt(RANDOM.nextInt(chars.length())));
        }
        return sb.toString();
    }
}