package com.learnwithak.orderservice.command.api.controller;

import com.learnwithak.orderservice.command.api.aggregate.OrderAggregate;
import com.learnwithak.orderservice.command.api.command.CreateOrderCommand;
import com.learnwithak.orderservice.command.api.model.OrderRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderCommandController {
    public static final Logger LOGGER = LoggerFactory.getLogger(OrderCommandController.class);
    private CommandGateway commandGateway;

    public OrderCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createOrder(@RequestBody OrderRestModel orderRestModel) {
    LOGGER.info("Create Order API Is called ...");
        String orderId = UUID.randomUUID().toString();

        CreateOrderCommand createOrderCommand
                = CreateOrderCommand.builder()
                .orderId(orderId)
                .addressId(orderRestModel.getAddressId())
                .productId(orderRestModel.getProductId())
                .quantity(orderRestModel.getQuantity())
                .orderStatus("CREATED")
                .build();
        LOGGER.info("Calling command Gateway from Order API ...");
        commandGateway.sendAndWait(createOrderCommand);

        return "Order Created";
    }
}
