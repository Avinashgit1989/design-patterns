package com.learnwithak.order.service.command.api.events;

import lombok.Data;

@Data
public class CreateOrderEvent {
    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private String orderStatus;

}
