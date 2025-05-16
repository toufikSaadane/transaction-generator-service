package com.toufik.transactiongeneratorservice.model;

import lombok.Data;

import java.util.Date;

@Data
public class Mt940Data {
    private String transactionReference;
    private String accountNumber;
    private String statementNumber;
    private String openingBalance;
    private Date valueDate;
    private Date entryDate;
    private String debitCredit;
    private String amount;
    private String closingBalance;
    private String eref;
    private String merchantName;
    private String remittanceInfo;
}
