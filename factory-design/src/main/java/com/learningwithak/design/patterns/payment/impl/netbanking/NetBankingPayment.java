package com.learningwithak.design.patterns.payment.impl.netbanking;

import com.learningwithak.design.patterns.payment.Payment;

public class NetBankingPayment implements Payment {
    @Override
    public void pay() {
        System.out.println("NetBanking payment");
    }
}
