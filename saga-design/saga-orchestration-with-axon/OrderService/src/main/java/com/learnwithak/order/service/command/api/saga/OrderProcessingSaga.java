package com.learnwithak.order.service.command.api.saga;

import com.learnwithak.common.service.commads.CompleteOrderCommand;
import com.learnwithak.common.service.commads.ShipmentOrderCommand;
import com.learnwithak.common.service.commads.ValidatePaymentCommand;
import com.learnwithak.common.service.events.OrderCompletedEvent;
import com.learnwithak.common.service.events.PaymentProcessedEvent;
import com.learnwithak.common.service.events.ShipmentProcessedEvent;
import com.learnwithak.common.service.model.User;
import com.learnwithak.common.service.queries.GetUserPaymentDetailsQuery;
import com.learnwithak.order.service.command.api.events.CreateOrderEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@NoArgsConstructor
//@AllArgsConstructor
public class OrderProcessingSaga {
    private static final  Logger logger = LoggerFactory.getLogger(OrderProcessingSaga.class);
    @Autowired
    private transient CommandGateway commandGateway;
    @Autowired
    private transient QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty ="orderId")
    public void handle(CreateOrderEvent createOrderEvent){
    logger.info("handle method started with CreateOrderEvent in Saga for orderId : {}"+ createOrderEvent.getOrderId());
        GetUserPaymentDetailsQuery getUserPaymentDetailsQuery =
                new GetUserPaymentDetailsQuery(createOrderEvent.getUserId());
        User user = null;
        //it returns completable

        try {
            //calling the queryGateway to get User and payment details
            logger.info("Calling query gateway to get userPaymentDetails...." );
            user = queryGateway.query(getUserPaymentDetailsQuery, ResponseTypes.instanceOf(User.class))
                    .join();
        }catch (Exception exception){
            logger.error("Error while getting user query :: "+ exception.getMessage());
            //start the Compensating transaction
        }
        ValidatePaymentCommand validatePaymentCommand = ValidatePaymentCommand.builder()
                .orderId(createOrderEvent.getOrderId())
                .paymentId(UUID.randomUUID().toString())
                .cardDetails(user.getCardDetails()).build();
        //Now sending this command CommandGateway
        logger.info("Sending command to commandGateway to validate payment Command ...." );
        commandGateway.sendAndWait(validatePaymentCommand);
    }
    //@StartSaga
    @SagaEventHandler(associationProperty ="orderId")
    public void handle(PaymentProcessedEvent paymentProcessedEvent){
        logger.info("PaymentProcessedEvent in Saga for orderId : {}"+
                paymentProcessedEvent.getOrderId());
        try {
            /*if(true)
                throw new Exception();*/

            ShipmentOrderCommand shipmentOrderCommand = ShipmentOrderCommand
                    .builder()
                    .shipmentId(UUID.randomUUID().toString())
                    .orderId(paymentProcessedEvent.getOrderId())
                    .build();
            logger.info("Command send to the command Gateway...");
            commandGateway.sendAndWait(shipmentOrderCommand);
        } catch (Exception exception) {
            logger.error("Error while getting user query :: "+ exception.getMessage());
            //start the Compensating transaction
        }
    }

   // @StartSaga
    @SagaEventHandler(associationProperty ="orderId")
    public void handle(ShipmentProcessedEvent shipmentProcessedEvent){
        logger.info("ShipmentProcessedEvent in Saga for orderId : {}"+
                shipmentProcessedEvent.getOrderId());
        try {
            CompleteOrderCommand completeOrderCommand = CompleteOrderCommand
                    .builder()
                    .orderId(UUID.randomUUID().toString())
                    .orderStatus("APPROVED")
                    .build();
            logger.info("Command send to the command Gateway...");
            commandGateway.sendAndWait(completeOrderCommand);
        } catch (Exception exception) {
            logger.error("Error while getting user query :: "+ exception.getMessage());
            //start the Compensating transaction
        }
    }

    @SagaEventHandler(associationProperty ="orderId")
    @EndSaga
    public void handle(OrderCompletedEvent orderCompletedEvent ){
        logger.info("OrderCompletedEvent in Saga for orderId : {}"+
                orderCompletedEvent.getOrderId());
        //Note: here we are not creating the invoice service so using the End saga here by just printing the log.
        /*try {
            CompleteOrderCommand completeOrderCommand = CompleteOrderCommand.builder()
                    .orderId(UUID.randomUUID().toString())
                    .orderStatus("APPROVED").build();
            logger.info("Command send to the command Gateway...");
            commandGateway.sendAndWait(completeOrderCommand);
        } catch (Exception exception) {
            logger.error("Error while getting user query :: "+ exception.getMessage());
            //start the Compensating transaction
        }*/
    }
}
