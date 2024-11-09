package com.learnwithak.shipment.service.command.api.events;

import com.learnwithak.common.service.events.ShipmentProcessedEvent;
import com.learnwithak.shipment.service.command.api.entity.Shipment;
import com.learnwithak.shipment.service.command.api.repository.ShipmentRepository;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ShipmentsEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(ShipmentsEventHandler.class);

    private ShipmentRepository shipmentRepository;
    @EventHandler
    public void on(ShipmentProcessedEvent shipmentProcessedEvent){
        Shipment shipment = Shipment.builder()
                .shipmentId(shipmentProcessedEvent.getShipmentId())
                .orderId(shipmentProcessedEvent.getOrderId())
                .shipmentStatus(shipmentProcessedEvent.getShipmentStatus()).build();
        logger.info("Shipment data saved to the database ..");
        shipmentRepository.save(shipment);

    }
}
