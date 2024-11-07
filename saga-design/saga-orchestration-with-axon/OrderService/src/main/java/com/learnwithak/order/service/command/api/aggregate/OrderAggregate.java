package com.learnwithak.order.service.command.api.aggregate;

import com.learnwithak.order.service.command.api.command.CreateOrderCommand;
import com.learnwithak.order.service.command.api.events.CreateOrderEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class OrderAggregate {
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
        CreateOrderEvent createOrderEvent = new CreateOrderEvent();
        BeanUtils.copyProperties(createOrderCommand, createOrderEvent);
        AggregateLifecycle.apply(createOrderEvent);
    }
@EventSourcingHandler
    public void on(CreateOrderEvent createOrderEvent){
        this.addressId = createOrderEvent.getAddressId();
        this.orderStatus = createOrderEvent.getOrderStatus();
        this.userId = createOrderEvent.getUserId();
        this.productId = createOrderEvent.getProductId();
        this.quantity = createOrderEvent.getQuantity();
        this.orderId = createOrderEvent.getOrderId();

    }
}
