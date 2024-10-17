package com.learningwithak.design.patterns.factory.impl;

import com.learningwithak.design.patterns.factory.PaymentFactory;
import com.learningwithak.design.patterns.payment.Payment;
import com.learningwithak.design.patterns.payment.impl.creadit.CreditCard;
import com.learningwithak.design.patterns.payment.impl.debitcard.DebitCardPayment;
import com.learningwithak.design.patterns.payment.impl.netbanking.NetBankingPayment;
import com.learningwithak.design.patterns.payment.impl.upi.UpiPayment;
import org.springframework.stereotype.Service;

@Service
public class PaymentFactoryImpl implements PaymentFactory {
    @Override
    public Payment createPayment(String paymentType) {
        if (paymentType == null || paymentType.isEmpty()) {
            return null;

        }
        switch (paymentType.toLowerCase()) {
            case "creadit":
                return new CreditCard();
            case "debit":
                return new DebitCardPayment();
            case "netbanking":
                return new NetBankingPayment();
            case "upi":
                return new UpiPayment();
            default:
                throw new IllegalArgumentException("Unknown paymentType " + paymentType);
        }
    }
}
