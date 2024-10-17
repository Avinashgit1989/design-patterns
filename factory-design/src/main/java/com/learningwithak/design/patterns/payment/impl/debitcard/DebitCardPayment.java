package com.learningwithak.design.patterns.payment.impl.debitcard;

import com.learningwithak.design.patterns.payment.Payment;

public class DebitCardPayment implements Payment {
    @Override
    public void pay() {
        System.out.println("Payment with debit card");
    }
}
