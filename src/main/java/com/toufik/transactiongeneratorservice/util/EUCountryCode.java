package com.toufik.transactiongeneratorservice.util;

public enum EUCountryCode {
    AT("Austria", 20),
    BE("Belgium", 16),
    BG("Bulgaria", 22),
    HR("Croatia", 21),
    CY("Cyprus", 28),
    CZ("Czech Republic", 24),
    DK("Denmark", 18),
    EE("Estonia", 20),
    FI("Finland", 18),
    FR("France", 27),
    DE("Germany", 22),
    GR("Greece", 27),
    HU("Hungary", 28),
    IE("Ireland", 22),
    IT("Italy", 27),
    LV("Latvia", 21),
    LT("Lithuania", 20),
    LU("Luxembourg", 20),
    MT("Malta", 31),
    NL("Netherlands", 18),
    PL("Poland", 28),
    PT("Portugal", 25),
    RO("Romania", 24),
    SK("Slovakia", 24),
    SI("Slovenia", 19),
    ES("Spain", 24),
    SE("Sweden", 24);

    private final String countryName;
    private final int ibanLength;

    EUCountryCode(String countryName, int ibanLength) {
        this.countryName = countryName;
        this.ibanLength = ibanLength;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getIbanCountryCode() {
        return this.name();
    }

    public int getIbanLength() {
        return ibanLength;
    }
}