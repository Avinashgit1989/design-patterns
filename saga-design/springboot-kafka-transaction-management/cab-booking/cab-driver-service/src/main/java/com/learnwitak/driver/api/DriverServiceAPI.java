package com.learnwitak.driver.api;

import com.learnwitak.common.driver.dto.DriverDto;
import com.learnwitak.driver.entity.Driver;
import com.learnwitak.driver.service.DriverService;
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
    public ResponseEntity<String> addNewDriver(@RequestBody DriverDto driver){
    LOGGER.info("Driver Service API to create New Driver  is called....");
        return new ResponseEntity<String>(driverService.addNewDriver(driver), HttpStatus.CREATED);
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
