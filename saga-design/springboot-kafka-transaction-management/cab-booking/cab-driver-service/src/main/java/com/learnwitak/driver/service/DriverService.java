package com.learnwitak.driver.service;

import com.learnwitak.common.driver.dto.DriverDto;
import com.learnwitak.common.emuns.CommonStatusEnum;
import com.learnwitak.driver.entity.Driver;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DriverService {
    String addNewDriver(DriverDto driver);

    List<Driver> getAllDrivers();

    Optional<Driver> getDriverById(UUID driverId);

    int updateDriverStatus(UUID driverId, CommonStatusEnum driverStatus);
}
