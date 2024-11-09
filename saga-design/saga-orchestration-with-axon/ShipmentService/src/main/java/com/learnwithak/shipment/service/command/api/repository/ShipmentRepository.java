package com.learnwithak.shipment.service.command.api.repository;

import com.learnwithak.shipment.service.command.api.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, String> {
}
