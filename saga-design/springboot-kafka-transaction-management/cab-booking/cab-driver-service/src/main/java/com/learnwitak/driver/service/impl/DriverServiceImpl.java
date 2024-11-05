package com.learnwitak.driver.service.impl;

import com.learnwitak.common.driver.dto.DriverDto;
import com.learnwitak.common.emuns.CommonStatusEnum;
import com.learnwitak.driver.entity.Driver;
import com.learnwitak.common.cab.events.CabEvent;
import com.learnwitak.driver.repository.DriverRepository;
import com.learnwitak.driver.service.DriverService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DriverServiceImpl implements DriverService {
    public final static Logger LOGGER = LoggerFactory.getLogger(DriverServiceImpl.class);
    private final DriverRepository driverRepository;
    private final KafkaTemplate<String, CabEvent> cabEventKafkaTemplate;
    private final String ADD_CAB_EVENT= "add_cab_event";

    @Override
    public String addNewDriver(DriverDto driverDto) {
        LOGGER.info("Enter into add New Driver Service Implementation class");
        Driver driver = driverRepository.save(Driver.builder()
                .driverName(driverDto.getDriverName())
                .driverEmail(driverDto.getDriverEmail())
                .driverLocation(driverDto.getDriverLocation())
                .driverStatus(CommonStatusEnum.PENDING).build());
        //creating cab event
        CabEvent cabEvent = CabEvent.builder()
                .driverId(driver.getDiverId())
                .registrationNumber(driverDto.getCabDto().getRegistrationNumber())
                .cabTypes(driverDto.getCabDto().getCabType())
                .build();
        //publishing cab event to kafka topic
        cabEventKafkaTemplate.send(ADD_CAB_EVENT, cabEvent);
        return "Driver details :: "+driver.getDiverId()+" is processed.";
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

    @Override
    @Transactional
    public int updateDriverStatus(UUID driverId, CommonStatusEnum driverStatus) {
        LOGGER.info("Enter into update a Driver status Service Implementation class");
            return driverRepository.updateDriverStatus(driverId, driverStatus);
    }
}
