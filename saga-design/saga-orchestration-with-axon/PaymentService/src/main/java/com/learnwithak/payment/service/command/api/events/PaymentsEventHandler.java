package com.learnwithak.payment.service.command.api.events;

import com.learnwithak.common.service.events.PaymentProcessedEvent;
import com.learnwithak.payment.service.command.api.entity.Payment;
import com.learnwithak.payment.service.command.api.repository.PaymentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PaymentsEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(PaymentsEventHandler.class);
    private PaymentRepository paymentRepository;

    public PaymentsEventHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @EventHandler
    public void on(PaymentProcessedEvent paymentProcessedEvent){
        logger.info("PaymentProcessedEvent called to saved payment in database...");
        Payment payment = Payment
                .builder()
                .paymentId(paymentProcessedEvent.getPaymentId())
                .orderId(paymentProcessedEvent.getOrderId())
                .paymentDate(new Date())
                .paymentStatus("COMPLETED")
                .build();
        paymentRepository.save(payment);
        logger.info("Payment details saved to database...");
    }
}
