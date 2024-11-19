package com.learnwithak.productservice.command.api.events;

import com.learnwithak.productservice.command.api.data.Product;
import com.learnwithak.productservice.command.api.data.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product")
public class ProductEventsHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProductEventsHandler.class);
    private ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {
        LOGGER.info("ProductCreateEvent handler called...");
        Product product =
                new Product();
        BeanUtils.copyProperties(event,product);
        productRepository.save(product);
        throw new Exception("Exception Occurred");
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}
