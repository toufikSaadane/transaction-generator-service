package com.toufik.transactiongeneratorservice.model;

import com.toufik.transactiongeneratorservice.util.EUCountryCode;
import lombok.Data;

/**
 * Represents an IBAN and its associated EU country code.
 * This class is used to encapsulate the IBAN and the country it belongs to.
 */
@Data
public class IbanAndCountry {
    private final String iban;
    private final EUCountryCode country;

    public IbanAndCountry(String iban, EUCountryCode country) {
        this.iban = iban;
        this.country = country;
    }

    public String getIban() { return iban; }
    public EUCountryCode getCountry() { return country; }
}
