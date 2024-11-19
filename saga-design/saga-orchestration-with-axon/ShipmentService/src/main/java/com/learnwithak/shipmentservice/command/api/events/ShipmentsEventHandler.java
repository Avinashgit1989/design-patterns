package com.learnwithak.shipmentservice.command.api.events;

import com.learningwithak.commonservice.events.OrderShippedEvent;
import com.learnwithak.shipmentservice.command.api.aggregate.ShipmentAggregate;
import com.learnwithak.shipmentservice.command.api.data.Shipment;
import com.learnwithak.shipmentservice.command.api.data.ShipmentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ShipmentsEventHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(ShipmentsEventHandler.class);
    private ShipmentRepository shipmentRepository;

    public ShipmentsEventHandler(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @EventHandler
    public void on(OrderShippedEvent event) {
        LOGGER.info("OrderShippedEvent Handler Called to persist data in shipment db.");
        Shipment shipment
                = new Shipment();
        BeanUtils.copyProperties(event,shipment);
        shipmentRepository.save(shipment);
    }
}
