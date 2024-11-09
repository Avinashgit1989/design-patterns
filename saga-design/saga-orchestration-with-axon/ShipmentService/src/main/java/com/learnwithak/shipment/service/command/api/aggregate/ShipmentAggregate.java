package com.learnwithak.shipment.service.command.api.aggregate;

import com.learnwithak.common.service.commads.ShipmentOrderCommand;
import com.learnwithak.common.service.events.ShipmentProcessedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ShipmentAggregate {
    @AggregateIdentifier
    private String shipmentId;
    private String orderId;
    private String shipmentStatus;

    public ShipmentAggregate() {
    }
    @CommandHandler
    public ShipmentAggregate(ShipmentOrderCommand shipmentOrderCommand) {
        //validate the Shipment details.
        //publish the shipment processed event.
        ShipmentProcessedEvent shipmentProcessedEvent =
                ShipmentProcessedEvent.builder()
                        .shipmentId(shipmentOrderCommand.getShipmentId())
                        .orderId(shipmentOrderCommand.getOrderId())
                        .shipmentStatus("COMPLETED").build();
        AggregateLifecycle.apply(shipmentProcessedEvent);
    }

    @EventSourcingHandler
    public void on(ShipmentProcessedEvent shipmentProcessedEvent){
        this.orderId = shipmentProcessedEvent.getOrderId();
        this.shipmentId = shipmentProcessedEvent.getShipmentStatus();
        this.shipmentStatus = shipmentProcessedEvent.getShipmentStatus();
    }
}
