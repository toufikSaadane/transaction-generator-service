package com.toufik.transactiongeneratorservice.service.mt103flow.MH103Headers.MT103Block4Header;

import com.toufik.transactiongeneratorservice.util.Field50kPerson;

import java.util.Random;

/**
 * Generates a random SWIFT MT103 Field 50K string using the Person enum.
 *
 * <p>Field 50K (Ordering Customer) includes account number, ordering customer's name, and address.</p>
 *
 * <p>Example output:</p>
 * <pre>
 * :50K:BE12345678901234
 * JOHN DOE
 * 1 MAIN STREET
 * BRUSSELS BE
 * </pre>
 */
public class Field50KOrderingCustomer {

    private static final Random RANDOM = new Random();

    /**
     * Returns a randomized Field 50K string with a random Person from enum.
     *
     * @return A valid SWIFT MT103 :50K: field string
     */
    public static String generateOrderingCustomer() {
        Field50kPerson person = randomPerson();
        String account = generateRandomIban();

        return ":50K:" + account + "\n" + person.getName() + "\n" + person.getAddress();
    }

    private static Field50kPerson randomPerson() {
        Field50kPerson[] persons = Field50kPerson.values();
        return persons[RANDOM.nextInt(persons.length)];
    }

    private static String generateRandomIban() {
        // Simple Belgian IBAN generator example
        long randomPart = Math.abs(RANDOM.nextLong() % 100000000000000L);
        return String.format("BE%02d%014d", 10 + RANDOM.nextInt(89), randomPart);
    }
}