package com.learnwitak.driver.entity;

import com.learnwitak.common.emuns.CommonStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "driverId")
    private UUID diverId;
    @Column(name = "driverName")
    private String driverName;
    @Column(name = "driverEmail")
    private String driverEmail;
    @Column(name = "driverLocation")
    private String driverLocation;
    @Enumerated(EnumType.STRING)
    @Column(name = "driver_status")
    private CommonStatusEnum driverStatus;
}
