package com.learnwithak.payment.service.command.api.repository;

import com.learnwithak.payment.service.command.api.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
}