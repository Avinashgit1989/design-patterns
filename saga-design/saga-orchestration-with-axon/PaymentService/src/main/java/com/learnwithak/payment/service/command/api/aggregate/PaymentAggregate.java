package com.learnwithak.payment.service.command.api.aggregate;

import com.learnwithak.common.service.commads.ValidatePaymentCommand;
import com.learnwithak.common.service.events.PaymentProcessedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aggregate
public class PaymentAggregate {
    private static final Logger logger = LoggerFactory.getLogger(PaymentAggregate.class);
    @AggregateIdentifier
    private String paymentId;
    private String orderId;
    private String paymentStatus;

    public PaymentAggregate() {
    }
    @CommandHandler
    public PaymentAggregate(ValidatePaymentCommand validatePaymentCommand) {
        logger.info("Enter into payment aggregate method...");
        //validate the payment details.
        //publish the payment processed event.
        logger.info("Executing Payment Aggregate command for " +
                "orderId : {} and paymentId :{}",
                validatePaymentCommand.getOrderId(),
                validatePaymentCommand.getPaymentId());
        PaymentProcessedEvent paymentProcessedEvent =
                new PaymentProcessedEvent(validatePaymentCommand.getOrderId(),
                        validatePaymentCommand.getPaymentId());

        AggregateLifecycle.apply(paymentProcessedEvent);
        logger.info("Payment processed event applied..");

    }

    @EventSourcingHandler
    public void on(PaymentProcessedEvent paymentProcessedEvent){
        this.orderId = paymentProcessedEvent.getOrderId();
        this.paymentId = paymentProcessedEvent.getPaymentId();
    }
}
