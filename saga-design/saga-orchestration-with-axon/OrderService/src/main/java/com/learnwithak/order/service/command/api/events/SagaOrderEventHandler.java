package com.learnwithak.order.service.command.api.events;

import com.learnwithak.common.service.events.OrderCompletedEvent;
import com.learnwithak.order.service.command.api.entity.Order;
import com.learnwithak.order.service.command.api.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SagaOrderEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(SagaOrderEventHandler.class);
    private OrderRepository orderRepository;
    @EventHandler
    public void on(CreateOrderEvent createOrderEvent){
        logger.info("CreateOrderEvent handler called to persist order data in order db..");
        Order order = new Order();
        BeanUtils.copyProperties(createOrderEvent, order);
        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderCompletedEvent orderCompletedEvent){
        logger.info("CreateOrderEvent handler called to updated order status data in order db..");
        Order order = orderRepository.findById(orderCompletedEvent.getOrderId()).get();
        order.setOrderStatus(orderCompletedEvent.getOrderStatus());
        orderRepository.save(order);
    }
}
