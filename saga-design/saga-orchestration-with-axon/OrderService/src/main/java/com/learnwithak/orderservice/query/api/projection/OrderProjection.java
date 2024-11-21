package com.learnwithak.orderservice.query.api.projection;

import com.learnwithak.orderservice.command.api.data.OrderRepository;
import com.learnwithak.orderservice.query.api.OrderResponseModel;
import com.learnwithak.orderservice.query.api.query.GetAllOrderDetailsQuery;
import com.learnwithak.orderservice.query.api.query.GetOrderDetailsByIdQuery;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderProjection {
    public static final Logger LOGGER = LoggerFactory.getLogger(OrderProjection.class);
    private OrderRepository orderRepository;
    @QueryHandler
    public List<OrderResponseModel> handle(GetAllOrderDetailsQuery getAllOrderDetailsQuery) {
        LOGGER.info("Query Handler called to get All Order details...");
        return orderRepository.findAll().stream()
                .map(order -> OrderResponseModel.builder()
                        .orderId(order.getOrderId())
                        .userId(order.getUserId())
                        .addressId(order.getAddressId())
                        .quantity(order.getQuantity())
                        .orderStatus(order.getOrderStatus())
                        .build())
                .collect(Collectors.toList());
    }

    @QueryHandler
    public OrderResponseModel handle(GetOrderDetailsByIdQuery getOrderDetailsByIdQuery) {
        LOGGER.info("Query Handler called to get order details by Id...");
        return orderRepository.findById(getOrderDetailsByIdQuery.getOrderId())
                .map(order -> OrderResponseModel.builder()
                        .orderId(order.getOrderId())
                        .userId(order.getUserId())
                        .addressId(order.getAddressId())
                        .quantity(order.getQuantity())
                        .orderStatus(order.getOrderStatus())
                        .build())
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }
}
