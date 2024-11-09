package com.learnwithak.common.service.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShipmentProcessedEvent {
    private String shipmentId;
    private String orderId;
    private String shipmentStatus;
}
