package com.toufik.transactiongeneratorservice.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Mt103Data {
    // Basic Header
    private String senderBic;           // {1:} (F01BANKBEBBAXXX)
    private String messageType;         // 103
    // Application Header
    private String receiverBic;         // {2:} (I103BANKDEFFXXXXN)
    // User Header
    private String userReference;       // {108:} (REF20250527)
    // Transaction Fields
    private String transactionReference;  // :20:
    private String bankOperationCode;     // :23B: (CRED)
    private Date valueDate;               // :32A:
    private String currency;              // :32A:
    private String amount;                // :32A:
    private String originalAmount;        // :33B:
    // Ordering Customer
    private String orderingAccount;       // :50K:
    private String orderingName;
    private String orderingAddress;
    // Banks
    private String orderingInstitution;   // :52A:
    private String senderCorrespondent;   // :53A:
    private String receiverCorrespondent; // :54A:
    private String intermediary;          // :56A:
    private String accountWithInstitution; // :57A:
    // Beneficiary
    private String beneficiaryAccount;    // :59:
    private String beneficiaryName;
    private String beneficiaryAddress;
    // Additional Info
    private String paymentDetails;        // :70:
    private String charges;               // :71A: (SHA)
    private String senderToReceiverInfo;  // :72:
    private String regulatoryReporting;   // :77B:
    // Trailer
    private String checksum;              // {CHK:}
}