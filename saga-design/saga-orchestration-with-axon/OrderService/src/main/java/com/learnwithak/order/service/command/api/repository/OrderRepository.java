package com.learnwithak.order.service.command.api.repository;

import com.learnwithak.order.service.command.api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
