package com.learnwithak.order.service.command.api.controller;

import com.learnwithak.order.service.command.api.aggregate.OrderAggregate;
import com.learnwithak.order.service.command.api.command.CreateOrderCommand;
import com.learnwithak.order.service.command.api.model.OrderModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/orders")
public class OrderCommandController {
    private static final Logger logger = LoggerFactory.getLogger(OrderCommandController.class);
    private CommandGateway commandGateway;

    public OrderCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderModel orderModel){
        logger.info("Create Order API is called ....");
        String orderId = UUID.randomUUID().toString();
        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .orderId(orderId)
                .productId(orderModel.getProductId())
                .addressId(orderModel.getAddressId())
                .quantity(orderModel.getQuantity())
                .orderStatus("CREATED")
                .build();
        commandGateway.sendAndWait(createOrderCommand);
        return new ResponseEntity<>("Order Created", HttpStatus.CREATED);
    }
}
