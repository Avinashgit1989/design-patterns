package com.learnwithak.orderservice.command.api.events;

import com.learningwithak.commonservice.events.OrderCancelledEvent;
import com.learningwithak.commonservice.events.OrderCompletedEvent;
import com.learnwithak.orderservice.command.api.controller.OrderCommandController;
import com.learnwithak.orderservice.command.api.data.Order;
import com.learnwithak.orderservice.command.api.data.OrderRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderEventsHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(OrderEventsHandler.class);
    private OrderRepository orderRepository;

    public OrderEventsHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        LOGGER.info("Event Handler Called to save Order data in database...");
        Order order = new Order();
        BeanUtils.copyProperties(event,order);
        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderCompletedEvent event) {
        LOGGER.info("Event Handler Called to Complete Order...");
        Order order
                = orderRepository.findById(event.getOrderId()).get();

        order.setOrderStatus(event.getOrderStatus());

        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderCancelledEvent event) {
        LOGGER.info("Event Handler Called to Cancel Order...");
        Order order
                = orderRepository.findById(event.getOrderId()).get();

        order.setOrderStatus(event.getOrderStatus());

        orderRepository.save(order);
    }
}
