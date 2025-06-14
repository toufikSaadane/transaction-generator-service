package com.toufik.transactiongeneratorservice.service.iban;

import com.toufik.transactiongeneratorservice.model.IbanAndCountry;
import com.toufik.transactiongeneratorservice.util.EUCountryCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class IbanGeneratorByCountry {

    private static final Random RANDOM = new Random();

    /**
     * Generates a random IBAN for a specified EU country.
     *
     * @param country The target EU country.
     * @return A randomly generated IBAN that respects the country format.
     */
    public static String generateIban(EUCountryCode country) {
        int length = country.getIbanLength();
        String countryCode = country.getIbanCountryCode();
        String checkDigits = String.format("%02d", RANDOM.nextInt(100));
        int bbanLength = length - 4;
        String bban = generateRandomAlphanumeric(bbanLength);
        return countryCode + checkDigits + bban;
    }

    public static IbanAndCountry generateRandomIbanAndCountry() {
        EUCountryCode[] countries = EUCountryCode.values();
        EUCountryCode randomCountry = countries[RANDOM.nextInt(countries.length)];
        String iban = generateIban(randomCountry);
        return new IbanAndCountry(iban, randomCountry);
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