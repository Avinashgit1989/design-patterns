package com.learnwithak.order.service.command.api.events;

import com.learnwithak.common.service.events.OrderCompletedEvent;
import com.learnwithak.order.service.command.api.command.CreateOrderCommand;
import com.learnwithak.order.service.command.api.entity.Order;
import com.learnwithak.order.service.command.api.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SagaOrderEventHandler {
    private OrderRepository orderRepository;
    @EventHandler
    public void on(CreateOrderEvent createOrderEvent){
        Order order = new Order();
        BeanUtils.copyProperties(createOrderEvent, order);
        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderCompletedEvent orderCompletedEvent){
        Order order = orderRepository.findById(orderCompletedEvent.getOrderId()).get();
        order.setOrderStatus(orderCompletedEvent.getOrderStatus());
        orderRepository.save(order);
    }
}
