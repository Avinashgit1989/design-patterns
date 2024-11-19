package com.learnwithak.paymentservice.command.api.events;

import com.learningwithak.commonservice.events.PaymentCancelledEvent;
import com.learningwithak.commonservice.events.PaymentProcessedEvent;
import com.learnwithak.paymentservice.command.api.aggregate.PaymentAggregate;
import com.learnwithak.paymentservice.command.api.data.Payment;
import com.learnwithak.paymentservice.command.api.data.PaymentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PaymentsEventHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(PaymentsEventHandler.class);
    private PaymentRepository paymentRepository;

    public PaymentsEventHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @EventHandler
    public void on(PaymentProcessedEvent event) {
        LOGGER.info("PaymentEventHandler called to persist payment data in database");
        Payment payment
                = Payment.builder()
                .paymentId(event.getPaymentId())
                .orderId(event.getOrderId())
                .paymentStatus("COMPLETED")
                .timeStamp(new Date())
                .build();

        paymentRepository.save(payment);
    }

    @EventHandler
    public void on(PaymentCancelledEvent event) {
        LOGGER.info("PaymentCancelledEvent called to update payment data in database");
        Payment payment
                = paymentRepository.findById(event.getPaymentId()).get();

        payment.setPaymentStatus(event.getPaymentStatus());

        paymentRepository.save(payment);
    }
}
