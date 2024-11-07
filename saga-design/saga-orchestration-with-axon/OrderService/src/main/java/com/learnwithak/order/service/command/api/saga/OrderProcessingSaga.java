package com.learnwithak.order.service.command.api.saga;

import com.learnwithak.common.service.commads.ValidatePaymentCommand;
import com.learnwithak.common.service.model.User;
import com.learnwithak.common.service.queries.GetUserPaymentDetailsQuery;
import com.learnwithak.order.service.command.api.events.CreateOrderEvent;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Saga
@AllArgsConstructor
public class OrderProcessingSaga {
    private static final  Logger logger = LoggerFactory.getLogger(OrderProcessingSaga.class);

    private CommandGateway commandGateway;
    private QueryGateway queryGateway;
    @StartSaga
    @SagaEventHandler(associationProperty ="orderId")
    public void handle(CreateOrderEvent createOrderEvent){
    logger.info("CreateOrderEvent in Saga for orderId : {}"+ createOrderEvent.getOrderId());
        GetUserPaymentDetailsQuery getUserPaymentDetailsQuery =
                new GetUserPaymentDetailsQuery(createOrderEvent.getUserId());
        User user = null;
        //it returns complitable

        try {
            //calling the queryGateway to get User and payment details
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
        commandGateway.sendAndWait(validatePaymentCommand);
    }
}
