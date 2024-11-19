package com.learnwithak.orderservice.command.api.aggregate;

import com.learningwithak.commonservice.commands.CancelOrderCommand;
import com.learningwithak.commonservice.commands.CompleteOrderCommand;
import com.learningwithak.commonservice.events.OrderCancelledEvent;
import com.learningwithak.commonservice.events.OrderCompletedEvent;
import com.learnwithak.orderservice.command.api.command.CreateOrderCommand;
import com.learnwithak.orderservice.command.api.events.OrderCreatedEvent;
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
public static final Logger LOGGER = LoggerFactory.getLogger(OrderAggregate.class);
    @AggregateIdentifier
    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private String orderStatus;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        //Validate The Command
        LOGGER.info("OrderAggregate method is called with orderId :{}, productId :{}",
                createOrderCommand.getOrderId(),
                createOrderCommand.getProductId());

        OrderCreatedEvent orderCreatedEvent
                = new OrderCreatedEvent();
        BeanUtils.copyProperties(createOrderCommand,
                orderCreatedEvent);
        AggregateLifecycle.apply(orderCreatedEvent);
        LOGGER.info("CreateOrderAggregateLifeCycle is applied.... ");
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderStatus = event.getOrderStatus();
        this.userId = event.getUserId();
        this.orderId = event.getOrderId();
        this.quantity = event.getQuantity();
        this.productId = event.getProductId();
        this.addressId = event.getAddressId();
    }

    @CommandHandler
    public void handle(CompleteOrderCommand completeOrderCommand) {
        LOGGER.info("CompleteOrderAggregate method is called with orderId :{}",
                completeOrderCommand.getOrderId());
        //Validate The Command
        // Publish Order Completed Event
        OrderCompletedEvent orderCompletedEvent
                = OrderCompletedEvent.builder()
                .orderStatus(completeOrderCommand.getOrderStatus())
                .orderId(completeOrderCommand.getOrderId())
                .build();
        AggregateLifecycle.apply(orderCompletedEvent);
        LOGGER.info("CompleteOrderAggregateLifeCycle is applied.... ");
    }

    @EventSourcingHandler
    public void on(OrderCompletedEvent event) {
        this.orderStatus = event.getOrderStatus();
    }

    @CommandHandler
    public void handle(CancelOrderCommand cancelOrderCommand) {
        LOGGER.info("CancelAggregate method is called with orderId :{}",
                cancelOrderCommand.getOrderId());
        OrderCancelledEvent orderCancelledEvent
                = new OrderCancelledEvent();
        BeanUtils.copyProperties(cancelOrderCommand,
                orderCancelledEvent);

        AggregateLifecycle.apply(orderCancelledEvent);
        LOGGER.info("CancelOrderAggregateLifeCycle is applied.... ");
    }

    @EventSourcingHandler
    public void on(OrderCancelledEvent event) {
        this.orderStatus = event.getOrderStatus();
    }
}
