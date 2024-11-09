package com.learnwithak.common.service.commads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class ShipmentOrderCommand {
    @TargetAggregateIdentifier
    private String shipmentId;
    private String orderId;
}
