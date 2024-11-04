package com.learnwitak.cab.service;

import com.learnwitak.cab.entity.Driver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DriverService {
    Driver addNewDriver(Driver driver);

    List<Driver> getAllDrivers();

    Optional<Driver> getDriverById(UUID driverId);
}
