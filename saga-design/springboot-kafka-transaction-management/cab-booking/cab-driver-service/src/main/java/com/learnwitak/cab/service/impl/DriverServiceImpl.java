package com.learnwitak.cab.service.impl;

import com.learnwitak.cab.entity.Driver;
import com.learnwitak.cab.repository.DriverRepository;
import com.learnwitak.cab.service.DriverService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DriverServiceImpl implements DriverService {
    public final static Logger LOGGER = LoggerFactory.getLogger(DriverServiceImpl.class);
    private final DriverRepository driverRepository;

    @Override
    public Driver addNewDriver(Driver driver) {
        LOGGER.info("Enter into add New Driver Service Implementation class");
        return driverRepository.save(driver);
    }

    @Override
    public List<Driver> getAllDrivers() {
        LOGGER.info("Enter into get All Drivers Service Implementation class");
        return driverRepository.findAll();
    }

    @Override
    public Optional<Driver> getDriverById(UUID driverId) {
        LOGGER.info("Enter into get a Driver Service Implementation class");
        return driverRepository.findById(driverId);
    }
}
