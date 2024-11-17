package com.learnwithak.order.service.command.api.aggregate;

import com.learnwithak.common.service.commads.CompleteOrderCommand;
import com.learnwithak.common.service.events.OrderCompletedEvent;
import com.learnwithak.order.service.command.api.command.CreateOrderCommand;
import com.learnwithak.order.service.command.api.events.CreateOrderEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

@Aggregate
public class OrderAggregate {
    private static final Logger logger = LoggerFactory.getLogger(OrderAggregate.class);
    @AggregateIdentifier
    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private String orderStatus;

    public OrderAggregate() { }
    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        logger.info("OrderAggregate :: method is being called ...  for CreateOrderCommand ");
        CreateOrderEvent createOrderEvent = new CreateOrderEvent();
        BeanUtils.copyProperties(createOrderCommand, createOrderEvent);
        AggregateLifecycle.apply(createOrderEvent);
    }
    @EventSourcingHandler
    public void on(CreateOrderEvent createOrderEvent){
        logger.info("on :: method of CreateOrderEvent is being called ...   ");
        this.addressId = createOrderEvent.getAddressId();
        this.orderStatus = createOrderEvent.getOrderStatus();
        this.userId = createOrderEvent.getUserId();
        this.productId = createOrderEvent.getProductId();
        this.quantity = createOrderEvent.getQuantity();
        this.orderId = createOrderEvent.getOrderId();

    }

    @CommandHandler
    public OrderAggregate(CompleteOrderCommand completeOrderCommand) {
        logger.info("OrderAggregate :: method of CreateOrderEvent is being called ...  for CompleteOrderCommand  ");
        //validate the command
        //publish the order complete Event
        OrderCompletedEvent orderCompletedEvent = OrderCompletedEvent.builder()
                .orderId(completeOrderCommand.getOrderId())
                .orderStatus(completeOrderCommand.getOrderStatus()).build();
        AggregateLifecycle.apply(orderCompletedEvent);
    }
    @EventSourcingHandler
    public void on(OrderCompletedEvent orderCompletedEvent){
        logger.info("on :: method of OrderCompletedEvent is being called ...   ");
        this.orderStatus = orderCompletedEvent.getOrderStatus();

    }
}
