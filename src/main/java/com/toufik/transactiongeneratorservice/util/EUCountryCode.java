package com.toufik.transactiongeneratorservice.util;

public enum EUCountryCode {
    AT("Austria"),
    BE("Belgium"),
    BG("Bulgaria"),
    HR("Croatia"),
    CY("Cyprus"),
    CZ("Czech Republic"),
    DK("Denmark"),
    EE("Estonia"),
    FI("Finland"),
    FR("France"),
    DE("Germany"),
    GR("Greece"),
    HU("Hungary"),
    IE("Ireland"),
    IT("Italy"),
    LV("Latvia"),
    LT("Lithuania"),
    LU("Luxembourg"),
    MT("Malta"),
    NL("Netherlands"),
    PL("Poland"),
    PT("Portugal"),
    RO("Romania"),
    SK("Slovakia"),
    SI("Slovenia"),
    ES("Spain"),
    SE("Sweden");

    private final String countryName;

    EUCountryCode(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getIbanCountryCode() {
        return this.name();
    }
}