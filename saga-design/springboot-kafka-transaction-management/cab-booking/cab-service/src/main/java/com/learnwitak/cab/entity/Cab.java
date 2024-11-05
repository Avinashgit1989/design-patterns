package com.learnwitak.cab.entity;

import com.learnwitak.cab.emuns.CommonStatusEnum;
import com.learnwitak.cab.types.CabTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.aot.generate.GeneratedTypeReference;
import org.springframework.boot.autoconfigure.web.WebProperties;

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
    @Column(name = "driverId")
    private UUID cabId;
    @Column(name = "registration_number")
    private String registrationNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "cab_types")
    private CabTypes cabTypes;
    @Enumerated(EnumType.STRING)
    @Column(name = "cab_status")
    private CommonStatusEnum cabStatus;
}
