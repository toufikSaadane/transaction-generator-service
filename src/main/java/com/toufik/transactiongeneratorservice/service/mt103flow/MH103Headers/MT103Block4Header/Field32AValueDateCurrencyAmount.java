package com.toufik.transactiongeneratorservice.service.mt103flow.MH103Headers.MT103Block4Header;

import com.toufik.transactiongeneratorservice.model.Mt103Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Appends Field 32A (Value Date / Currency / Amount) to a SWIFT MT103 message.
 *
 * <p>Field 32A is used to specify the settlement date, currency, and amount
 * of the payment.</p>
 *
 * <p>Format: :32A:yyMMdd[Currency][Amount]</p>
 *
 * <p>Example:</p>
 * <pre>
 * :32A:250527EUR12345,67
 * </pre>
 */
public class Field32AValueDateCurrencyAmount {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyMMdd");
    private static final String[] CURRENCIES = { "EUR", "USD", "GBP", "CHF", "JPY" };
    private static final Random RANDOM = new Random();

    /**
     * Returns a randomized Field 32A string with value date, currency, and amount.
     *
     * @return A valid SWIFT MT103 :32A: field string
     */
    public static String generateValueDateCurrencyAmount() {
        String valueDate = DATE_FORMAT.format(randomDate());
        String currency = CURRENCIES[RANDOM.nextInt(CURRENCIES.length)];
        String amount = generateRandomAmount();

        return ":32A:" + valueDate + currency + amount;
    }

    private static Date randomDate() {
        long now = System.currentTimeMillis();
        long daysBack = RANDOM.nextInt(30); // up to 30 days in the past
        return new Date(now - daysBack * 24 * 60 * 60 * 1000L);
    }

    private static String generateRandomAmount() {
        int whole = RANDOM.nextInt(99999) + 1; // 1 to 99999
        int decimal = RANDOM.nextInt(100);     // 0 to 99
        return String.format(Locale.US, "%d,%02d", whole, decimal); // SWIFT uses comma
    }
}