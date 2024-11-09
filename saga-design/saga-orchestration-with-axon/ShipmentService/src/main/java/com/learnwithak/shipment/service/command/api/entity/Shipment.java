package com.learnwithak.shipment.service.command.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "shipment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    @Id
    private String shipmentId;
    private String orderId;
    private String shipmentStatus;

}
