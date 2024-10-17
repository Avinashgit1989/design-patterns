package com.learningwithak.design.patterns.factory;

import com.learningwithak.design.patterns.payment.Payment;

public interface PaymentFactory {
    Payment createPayment(String paymentType);
}
