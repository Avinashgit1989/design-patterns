package com.learningwithak.design.patterns.payment.impl.creadit;

import com.learningwithak.design.patterns.payment.Payment;

public class CreditCard implements Payment {
    @Override
    public void pay() {
        System.out.println("pay with credit card");
    }
}
