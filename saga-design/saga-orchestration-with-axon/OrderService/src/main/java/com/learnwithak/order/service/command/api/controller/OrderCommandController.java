package com.learnwithak.order.service.command.api.controller;

import com.learnwithak.order.service.command.api.command.CreateOrderCommand;
import com.learnwithak.order.service.command.api.model.OrderModel;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class OrderCommandController {
    private CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderModel orderModel){
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
