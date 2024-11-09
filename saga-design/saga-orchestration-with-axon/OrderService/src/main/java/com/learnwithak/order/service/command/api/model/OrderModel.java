package com.learnwithak.order.service.command.api.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
}
