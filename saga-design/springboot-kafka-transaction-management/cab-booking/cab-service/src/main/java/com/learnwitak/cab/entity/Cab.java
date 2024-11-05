package com.learnwitak.cab.entity;

import com.learnwitak.common.emuns.CommonStatusEnum;
import com.learnwitak.common.cab.types.CabTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Builder
public class Cab {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cabId")
    private UUID cabId;
    @Column(name = "driverId")
    private UUID driverId;
    @Column(name = "registration_number")
    private String registrationNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "cab_types")
    private CabTypes cabTypes;
    @Enumerated(EnumType.STRING)
    @Column(name = "cab_status")
    private CommonStatusEnum cabStatus;
}
