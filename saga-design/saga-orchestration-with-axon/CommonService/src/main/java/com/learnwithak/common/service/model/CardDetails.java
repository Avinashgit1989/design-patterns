package com.learnwithak.common.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDetails {
    private String cardHolderName;
    private Integer cardNumber;
    private Integer issueMonth;
    private Integer issueYear;
    private Integer expiryMonth;
    private Integer expiryYear;
    private Integer cvv;



}
