package com.learnwitak.driver.kafka.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnwitak.common.driver.event.UpdateDriverStatusEvent;
import com.learnwitak.driver.service.DriverService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DriverKafkaListener {
    public final static Logger LOGGER = LoggerFactory.getLogger(DriverKafkaListener.class);
    private final DriverService driverService;

    private final String UPDATE_DRIVER_STATUS_EVENT= "update_driver_status_event";

    @KafkaListener(topics = UPDATE_DRIVER_STATUS_EVENT, groupId = "cab-group")
    public void updateDriverEvent(String event) throws JsonProcessingException {
        UpdateDriverStatusEvent updateDriverStatusEvent = new ObjectMapper()
                .readValue(event, UpdateDriverStatusEvent.class);
         driverService.updateDriverStatus(updateDriverStatusEvent.getDriverId(), updateDriverStatusEvent.getDriverStatus());

    }

}
