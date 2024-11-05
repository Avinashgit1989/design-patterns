package com.learnwitak.cab.repository;

import com.learnwitak.common.emuns.CommonStatusEnum;
import com.learnwitak.cab.entity.Cab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CabRepository extends JpaRepository<Cab, UUID> {
    boolean existsByRegistrationNumberAndCabStatus(String registrationNumber, CommonStatusEnum success);
}
