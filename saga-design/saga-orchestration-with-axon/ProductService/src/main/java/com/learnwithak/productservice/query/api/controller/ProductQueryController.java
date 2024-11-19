package com.learnwithak.productservice.query.api.controller;

import com.learnwithak.productservice.command.api.aggregate.ProductAggregate;
import com.learnwithak.productservice.command.api.model.ProductRestModel;
import com.learnwithak.productservice.query.api.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProductQueryController.class);
    private QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductRestModel> getAllProducts() {
        LOGGER.info("Get All Products API called... ");
        GetProductsQuery getProductsQuery =
                new GetProductsQuery();

        List<ProductRestModel> productRestModels =
        queryGateway.query(getProductsQuery,
                ResponseTypes.multipleInstancesOf(ProductRestModel.class))
                .join();

        return productRestModels;
    }
}
