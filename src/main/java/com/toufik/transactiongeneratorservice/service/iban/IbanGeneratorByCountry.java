package com.toufik.transactiongeneratorservice.service.iban;

import com.toufik.transactiongeneratorservice.util.EUCountryCode;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

@Service
public class IbanGeneratorByCountry {

    private static final Random RANDOM = new Random();
    private static final Map<EUCountryCode, Integer> IBAN_LENGTHS = new EnumMap<>(EUCountryCode.class);

    static {
        IBAN_LENGTHS.put(EUCountryCode.AT, 20);
        IBAN_LENGTHS.put(EUCountryCode.BE, 16);
        IBAN_LENGTHS.put(EUCountryCode.BG, 22);
        IBAN_LENGTHS.put(EUCountryCode.HR, 21);
        IBAN_LENGTHS.put(EUCountryCode.CY, 28);
        IBAN_LENGTHS.put(EUCountryCode.CZ, 24);
        IBAN_LENGTHS.put(EUCountryCode.DK, 18);
        IBAN_LENGTHS.put(EUCountryCode.EE, 20);
        IBAN_LENGTHS.put(EUCountryCode.FI, 18);
        IBAN_LENGTHS.put(EUCountryCode.FR, 27);
        IBAN_LENGTHS.put(EUCountryCode.DE, 22);
        IBAN_LENGTHS.put(EUCountryCode.GR, 27);
        IBAN_LENGTHS.put(EUCountryCode.HU, 28);
        IBAN_LENGTHS.put(EUCountryCode.IE, 22);
        IBAN_LENGTHS.put(EUCountryCode.IT, 27);
        IBAN_LENGTHS.put(EUCountryCode.LV, 21);
        IBAN_LENGTHS.put(EUCountryCode.LT, 20);
        IBAN_LENGTHS.put(EUCountryCode.LU, 20);
        IBAN_LENGTHS.put(EUCountryCode.MT, 31);
        IBAN_LENGTHS.put(EUCountryCode.NL, 18);
        IBAN_LENGTHS.put(EUCountryCode.PL, 28);
        IBAN_LENGTHS.put(EUCountryCode.PT, 25);
        IBAN_LENGTHS.put(EUCountryCode.RO, 24);
        IBAN_LENGTHS.put(EUCountryCode.SK, 24);
        IBAN_LENGTHS.put(EUCountryCode.SI, 19);
        IBAN_LENGTHS.put(EUCountryCode.ES, 24);
        IBAN_LENGTHS.put(EUCountryCode.SE, 24);
    }

    /**
     * Generates a random IBAN for a specified EU country.
     *
     * @param country The target EU country.
     * @return A randomly generated IBAN that respects the country format.
     */
    public static String generateIban(EUCountryCode country) {
        int length = IBAN_LENGTHS.get(country);
        // Country code (2-digits) from enum
        String countryCode = country.getIbanCountryCode();
        // Two check digits
        String checkDigits = String.format("%02d", RANDOM.nextInt(100));
        // BBAN: remainder of the IBAN length
        int bbanLength = length - 4;
        String bban = generateRandomAlphanumeric(bbanLength);
        return countryCode + checkDigits + bban;
    }

    /**
     * Randomly selects an EU country and generates its IBAN.
     *
     * @return A randomly generated IBAN.
     */
    public static String generateRandomIban() {
        EUCountryCode[] countries = EUCountryCode.values();
        EUCountryCode randomCountry = countries[RANDOM.nextInt(countries.length)];
        return generateIban(randomCountry);
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