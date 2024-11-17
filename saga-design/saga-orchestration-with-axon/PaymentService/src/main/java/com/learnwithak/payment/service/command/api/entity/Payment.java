package com.learnwithak.payment.service.command.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "payment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
   // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String paymentId;
    private String orderId;
    private Date paymentDate;
    private String paymentStatus;
}
