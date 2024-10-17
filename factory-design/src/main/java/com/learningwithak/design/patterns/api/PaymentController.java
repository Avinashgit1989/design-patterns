package com.learningwithak.design.patterns.api;

import com.learningwithak.design.patterns.factory.PaymentFactory;
import com.learningwithak.design.patterns.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private final PaymentFactory paymentFactory;

    public PaymentController(PaymentFactory paymentFactory) {
        this.paymentFactory =  paymentFactory;
    }

    @GetMapping("/payment")
    public String payment(@RequestParam String paymentType) {
        Payment payment = paymentFactory.createPayment(paymentType);
        if (payment == null) {
            return "Invalid payment type";
        }
        payment.pay();
        return "Payment done via " + paymentType;
    }
}