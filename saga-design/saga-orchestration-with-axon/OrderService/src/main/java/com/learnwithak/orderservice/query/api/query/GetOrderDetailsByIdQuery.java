package com.learnwithak.orderservice.query.api.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetOrderDetailsByIdQuery {
    private String orderId;
}
