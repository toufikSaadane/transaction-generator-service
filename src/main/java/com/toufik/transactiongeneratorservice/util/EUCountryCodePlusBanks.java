package com.toufik.transactiongeneratorservice.util;

    import java.util.Map;
    import java.util.HashMap;

    public enum EUCountryCodePlusBanks {

        AT("Austria", 20, Map.of(
                "BKAU", "Bank Austria",
                "GIBA", "Erste Group Bank",
                "OBKL", "Oberbank",
                "RZBA", "Raffishness Bank"
        )),
        BE("Belgium", 16, Map.of(
                "GKCC", "Belfius Bank",
                "KRED", "KBC Bank",
                "BBRU", "ING Belgium",
                "ARSP", "Argenta"
        )),
        BG("Bulgaria", 22, Map.of(
                "BNBG", "Bulgarian National Bank",
                "STSABGSF", "UniCredit Bulbank",
                "DEMIBGSF", "DSK Bank",
                "BPBIBGSF", "Postbank"
        )),
        HR("Croatia", 21, Map.of(
                "HRBA", "Privredna Banka Zagreb",
                "ZABA", "Zagrebačka Banka",
                "PBZG", "OTP Banka Hrvatska",
                "CROA", "Croatia Banka"
        )),
        CY("Cyprus", 28, Map.of(
                "BCYP", "Bank of Cyprus",
                "HEFA", "Hellenic Bank",
                "ECCY", "Eurobank Cyprus",
                "RCYB", "RCB Bank"
        )),
        CZ("Czech Republic", 24, Map.of(
                "KOMB", "Komerční Banka",
                "AGBC", "Česká Spořitelna",
                "RZBC", "Raiffeisenbank",
                "CNBA", "ČNB"
        )),
        DK("Denmark", 18, Map.of(
                "DABA", "Danske Bank",
                "NDEA", "Nordea Denmark",
                "JYBA", "Jyske Bank",
                "ALKL", "Alm. Brand Bank"
        )),
        EE("Estonia", 20, Map.of(
                "HABA", "Swedbank Estonia",
                "EEUH", "SEB Pank",
                "LHVB", "LHV Pank",
                "NDEA", "Nordea Estonia"
        )),
        FI("Finland", 18, Map.of(
                "NDEA", "Nordea Finland",
                "OKOY", "OP Financial Group",
                "DABF", "Danske Bank Finland",
                "SBAN", "S-Bank"
        )),
        FR("France", 27, Map.of(
                "BNPA", "BNP Paribas",
                "SOGE", "Société Générale",
                "CRLY", "Crédit Lyonnais",
                "CCFR", "Crédit Agricole"
        )),
        DE("Germany", 22, Map.of(
                "DEUT", "Deutsche Bank",
                "COBA", "Commerzbank",
                "HYVE", "HypoVereinsbank",
                "DRES", "Dresdner Bank"
        )),
        GR("Greece", 27, Map.of(
                "NBGR", "National Bank of Greece",
                "ERBK", "Eurobank Ergasias",
                "PIRB", "Piraeus Bank",
                "ALPHA", "Alpha Bank"
        )),
        HU("Hungary", 28, Map.of(
                "OTPV", "OTP Bank",
                "BUDA", "Budapest Bank",
                "MANE", "MKB Bank",
                "KKHB", "K&H Bank"
        )),
        IE("Ireland", 22, Map.of(
                "BOFI", "Bank of Ireland",
                "AIBK", "Allied Irish Banks",
                "ULSB", "Ulster Bank",
                "PTSB", "Permanent TSB"
        )),
        IT("Italy", 27, Map.of(
                "UNCR", "UniCredit",
                "INTE", "Intesa Sanpaolo",
                "BCIT", "Banca d'Italia",
                "BPIT", "Banca Popolare"
        )),
        LV("Latvia", 21, Map.of(
                "RIKO", "Rietumu Banka",
                "HABA", "Swedbank Latvia",
                "UNLA", "SEB Latvia",
                "CITI", "Citadele Bank"
        )),
        LT("Lithuania", 20, Map.of(
                "AGBLLT", "SEB Lithuania",
                "LIAB", "Swedbank Lithuania",
                "CBVILT", "Citadele Bank",
                "INDULT", "Siauliu Bankas"
        )),
        LU("Luxembourg", 20, Map.of(
                "BCEE", "Banque et Caisse d'Épargne de l'État",
                "BILL", "Banque Internationale à Luxembourg",
                "BSUI", "Banque de Luxembourg",
                "CCRALULL", "Raiffeisen Bank"
        )),
        MT("Malta", 31, Map.of(
                "VALL", "Bank of Valletta",
                "BMEM", "HSBC Bank Malta",
                "MABB", "APS Bank",
                "BMML", "Banif Bank"
        )),
        NL("Netherlands", 18, Map.of(
                "ABNA", "ABN AMRO",
                "INGB", "ING Bank",
                "RABO", "Rabobank",
                "SNSB", "SNS Bank"
        )),
        PL("Poland", 28, Map.of(
                "BPKO", "PKO Bank Polski",
                "BREX", "mBank",
                "WBKP", "Bank Zachodni WBK",
                "AGOP", "Bank Pekao"
        )),
        PT("Portugal", 25, Map.of(
                "CGD", "Caixa Geral de Depósitos",
                "BPIP", "Banco BPI",
                "BCOMP", "Banco Comercial Português",
                "BNPA", "Novo Banco"
        )),
        RO("Romania", 24, Map.of(
                "BRDE", "BRD - Groupe Société Générale",
                "BTRL", "Banca Transilvania",
                "RNCB", "CEC Bank",
                "UGBI", "Raiffeisen Bank"
        )),
        SK("Slovakia", 24, Map.of(
                "GIBASKBX", "VUB Banka",
                "TATRSKBX", "Tatra Banka",
                "SLZBSKBX", "Slovenská Sporiteľňa",
                "UNCRSKBX", "UniCredit Bank"
        )),
        SI("Slovenia", 19, Map.of(
                "LJBASI2X", "Nova Ljubljanska Banka",
                "HAABSI2X", "NLB Banka",
                "SABRSI2X", "Sberbank Slovenia",
                "KREKSI2X", "SKB Banka"
        )),
        ES("Spain", 24, Map.of(
                "BSCH", "Banco Santander",
                "BBVA", "BBVA",
                "CAIX", "CaixaBank",
                "BCOE", "Banco Sabadell"
        )),
        SE("Sweden", 24, Map.of(
                "SWED", "Swedbank",
                "HAND", "Handelsbanken",
                "NDEA", "Nordea Sweden",
                "SEB", "SEB"
        ));

        private final String countryName;
        private final int ibanLength;
        private final Map<String, String> bankCodes;

        EUCountryCodePlusBanks(String countryName, int ibanLength, Map<String, String> bankCodes) {
            this.countryName = countryName;
            this.ibanLength = ibanLength;
            this.bankCodes = bankCodes;
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

        public Map<String, String> getBankCodes() {
            return new HashMap<>(bankCodes);
        }
    }