package com.toufik.transactiongeneratorservice.service.mt103flow.MH103Headers.MT103Block4Header;

import java.util.Locale;
import java.util.Random;

/**
 * Generates a random SWIFT MT103 Field 33B string in the format:
 * :33B:[Currency][Amount]
 *
 * <p>Field 33B specifies the amount and currency of the transaction in the currency of the sender.</p>
 *
 * <p>Example output:</p>
 * <pre>
 * :33B:USD45678,90
 * </pre>
 */
public class Field33BCurrencyAmount {

    private static final String[] CURRENCIES = { "EUR", "USD", "GBP", "CHF", "JPY" };
    private static final Random RANDOM = new Random();

    /**
     * Returns a randomized Field 33B string with currency and amount.
     *
     * @return A valid SWIFT MT103 :33B: field string
     */
    public static String generateCurrencyAmount() {
        String currency = CURRENCIES[RANDOM.nextInt(CURRENCIES.length)];
        String amount = generateRandomAmount();

        return ":33B:" + currency + amount;
    }

    private static String generateRandomAmount() {
        int whole = RANDOM.nextInt(99999) + 1; // 1 to 99999
        int decimal = RANDOM.nextInt(100);     // 0 to 99
        return String.format(Locale.US, "%d,%02d", whole, decimal); // Comma decimal format
    }
}
