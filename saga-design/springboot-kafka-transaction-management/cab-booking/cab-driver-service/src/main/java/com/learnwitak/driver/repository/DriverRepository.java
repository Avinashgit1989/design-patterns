package com.learnwitak.driver.repository;

import com.learnwitak.common.emuns.CommonStatusEnum;
import com.learnwitak.driver.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface DriverRepository extends JpaRepository<Driver, UUID> {

    @Modifying
    @Query("UPDATE Driver d SET d.driverStatus= :status WHERE d.diverId= :driverId")
    int updateDriverStatus(@Param("driverId") UUID driverId, @Param("status") CommonStatusEnum status);
}
