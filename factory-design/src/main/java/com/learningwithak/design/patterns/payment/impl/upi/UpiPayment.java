package com.learningwithak.design.patterns.payment.impl.upi;

import com.learningwithak.design.patterns.payment.Payment;

public class UpiPayment implements Payment {
    @Override
    public void pay() {
        System.out.println("Payment with UPI");
    }
}
