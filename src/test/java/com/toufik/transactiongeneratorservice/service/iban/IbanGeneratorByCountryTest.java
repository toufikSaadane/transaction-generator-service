package com.toufik.transactiongeneratorservice.service.iban;

import com.toufik.transactiongeneratorservice.util.EUCountryCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class IbanGeneratorByCountryTest {

    @ParameterizedTest(name = "{0} IBAN should start with its country code and have length {1}")
    @MethodSource("ibanArgumentsProvider")
    void ibanForCountryMatchesExpectedFormat(EUCountryCode country, int expectedLength) {
        String iban = IbanGeneratorByCountry.generateIban(country);
        Assertions.assertNotNull(iban);
        Assertions.assertEquals(expectedLength, iban.length());
        Assertions.assertTrue(iban.startsWith(country.getIbanCountryCode()));
    }

    private static Stream<Arguments> ibanArgumentsProvider() {
        return Stream.of(
                Arguments.of(EUCountryCode.AT, 20),
                Arguments.of(EUCountryCode.BE, 16),
                Arguments.of(EUCountryCode.BG, 22),
                Arguments.of(EUCountryCode.HR, 21),
                Arguments.of(EUCountryCode.CY, 28),
                Arguments.of(EUCountryCode.CZ, 24),
                Arguments.of(EUCountryCode.DK, 18),
                Arguments.of(EUCountryCode.EE, 20),
                Arguments.of(EUCountryCode.FI, 18),
                Arguments.of(EUCountryCode.FR, 27),
                Arguments.of(EUCountryCode.DE, 22),
                Arguments.of(EUCountryCode.GR, 27),
                Arguments.of(EUCountryCode.HU, 28),
                Arguments.of(EUCountryCode.IE, 22),
                Arguments.of(EUCountryCode.IT, 27),
                Arguments.of(EUCountryCode.LV, 21),
                Arguments.of(EUCountryCode.LT, 20),
                Arguments.of(EUCountryCode.LU, 20),
                Arguments.of(EUCountryCode.MT, 31),
                Arguments.of(EUCountryCode.NL, 18),
                Arguments.of(EUCountryCode.PL, 28),
                Arguments.of(EUCountryCode.PT, 25),
                Arguments.of(EUCountryCode.RO, 24),
                Arguments.of(EUCountryCode.SK, 24),
                Arguments.of(EUCountryCode.SI, 19),
                Arguments.of(EUCountryCode.ES, 24),
                Arguments.of(EUCountryCode.SE, 24)
        );
    }

    @Test
    void randomIbanProducesValidCountryCodeAndCorrectLength() {
        String randomIban = IbanGeneratorByCountry.generateRandomIban();
        Assertions.assertNotNull(randomIban);
        String countryCode = randomIban.substring(0, 2);
        EUCountryCode matchedCountry = null;
        for (EUCountryCode country : EUCountryCode.values()) {
            if (country.getIbanCountryCode().equals(countryCode)) {
                matchedCountry = country;
                break;
            }
        }
        Assertions.assertNotNull(matchedCountry, "Random IBAN should start with a valid EU country code");
        int expectedLength = 0;
        switch (matchedCountry) {
            case AT: expectedLength = 20; break;
            case BE: expectedLength = 16; break;
            case BG: expectedLength = 22; break;
            case HR: expectedLength = 21; break;
            case CY: expectedLength = 28; break;
            case CZ: expectedLength = 24; break;
            case DK: expectedLength = 18; break;
            case EE: expectedLength = 20; break;
            case FI: expectedLength = 18; break;
            case FR: expectedLength = 27; break;
            case DE: expectedLength = 22; break;
            case GR: expectedLength = 27; break;
            case HU: expectedLength = 28; break;
            case IE: expectedLength = 22; break;
            case IT: expectedLength = 27; break;
            case LV: expectedLength = 21; break;
            case LT: expectedLength = 20; break;
            case LU: expectedLength = 20; break;
            case MT: expectedLength = 31; break;
            case NL: expectedLength = 18; break;
            case PL: expectedLength = 28; break;
            case PT: expectedLength = 25; break;
            case RO: expectedLength = 24; break;
            case SK: expectedLength = 24; break;
            case SI: expectedLength = 19; break;
            case ES: expectedLength = 24; break;
            case SE: expectedLength = 24; break;
        }
        Assertions.assertEquals(expectedLength, randomIban.length());
    }

    @Test
    void generateIbanWithNullCountryThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            IbanGeneratorByCountry.generateIban(null);
        });
    }
}