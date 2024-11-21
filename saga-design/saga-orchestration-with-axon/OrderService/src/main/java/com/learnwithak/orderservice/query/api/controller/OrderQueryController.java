package com.learnwithak.orderservice.query.api.controller;

import com.learnwithak.orderservice.command.api.data.Order;
import com.learnwithak.orderservice.query.api.OrderResponseModel;
import com.learnwithak.orderservice.query.api.query.GetAllOrderDetailsQuery;
import com.learnwithak.orderservice.query.api.query.GetOrderDetailsByIdQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderQueryController {
    public static final Logger LOGGER = LoggerFactory.getLogger(OrderQueryController.class);
    private transient QueryGateway queryGateway;

    public OrderQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseModel> getOrderDetails(@PathVariable String orderId){
        LOGGER.info("Get a Order details API is called...");
        GetOrderDetailsByIdQuery getOrderDetailsByIdQuery
                = new GetOrderDetailsByIdQuery(orderId);
        OrderResponseModel order =
                queryGateway.query(getOrderDetailsByIdQuery,
                        ResponseTypes.instanceOf(OrderResponseModel.class)).join();

        return new ResponseEntity<OrderResponseModel>(order, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<OrderResponseModel>> getAllOrderDetails(){
        LOGGER.info("Get All Order details API is called...");
        GetAllOrderDetailsQuery getAllOrderDetailsQuery
                = new GetAllOrderDetailsQuery();
        List<OrderResponseModel> orders =
                queryGateway.query(getAllOrderDetailsQuery,
                        ResponseTypes.multipleInstancesOf(OrderResponseModel.class)).join();

        return new ResponseEntity<List<OrderResponseModel>>(orders, HttpStatus.OK);
    }
}
