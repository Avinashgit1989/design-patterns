package com.learnwitak.cab.api;

import com.learnwitak.cab.entity.Driver;
import com.learnwitak.cab.service.DriverService;
import com.learnwitak.cab.service.impl.DriverServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/drivers")
@AllArgsConstructor
public class DriverServiceAPI {
    public final static Logger LOGGER = LoggerFactory.getLogger(DriverServiceAPI.class);
    @Autowired
    private final DriverService driverService;
@PostMapping
    public ResponseEntity<Driver> addNewDriver(@RequestBody Driver driver){
    LOGGER.info("Driver Service API to create New Driver  is called....");
        return new ResponseEntity<Driver>(driverService.addNewDriver(driver), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers(){
        LOGGER.info("Driver Service API to get All Drivers  is called....");
        return new ResponseEntity<List<Driver>>(driverService.getAllDrivers(), HttpStatus.OK);
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<Optional<Driver>> getDriverByDriverId(UUID driverId){
        LOGGER.info("Driver Service API to get a Driver  is called....");
        return new ResponseEntity<Optional<Driver>>(driverService.getDriverById(driverId), HttpStatus.OK);
    }
}
