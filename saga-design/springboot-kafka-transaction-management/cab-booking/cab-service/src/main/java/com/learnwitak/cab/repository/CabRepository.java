package com.learnwitak.cab.repository;

import com.learnwitak.cab.entity.Cab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CabRepository extends JpaRepository<Cab, UUID> {
}
