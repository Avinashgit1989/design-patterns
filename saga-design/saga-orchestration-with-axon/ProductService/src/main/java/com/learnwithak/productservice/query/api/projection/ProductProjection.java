package com.learnwithak.productservice.query.api.projection;

import com.learnwithak.productservice.command.api.data.Product;
import com.learnwithak.productservice.command.api.data.ProductRepository;
import com.learnwithak.productservice.command.api.model.ProductRestModel;
import com.learnwithak.productservice.query.api.controller.ProductQueryController;
import com.learnwithak.productservice.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProductProjection.class);
    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery) {
        LOGGER.info("Query Handler called to get All Product...");
        List<Product> products =
                productRepository.findAll();

        List<ProductRestModel> productRestModels =
                products.stream()
                        .map(product -> ProductRestModel
                                .builder()
                                .quantity(product.getQuantity())
                                .price(product.getPrice())
                                .name(product.getName())
                                .build())
                        .collect(Collectors.toList());

        return productRestModels;
    }
}
