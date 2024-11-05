package com.learnwitak.cab.kafka.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnwitak.common.driver.event.UpdateDriverStatusEvent;
import com.learnwitak.common.emuns.CommonStatusEnum;
import com.learnwitak.cab.entity.Cab;
import com.learnwitak.common.cab.events.CabEvent;
import com.learnwitak.cab.service.CabService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CabListener {
    public final static Logger LOGGER = LoggerFactory.getLogger(CabListener.class);
    private final CabService cabService;
    private KafkaTemplate<String, UpdateDriverStatusEvent> updateDriverStatusEventKafkaTemplate;
    private final String ADD_CAB_EVENT= "add_cab_event";
    private final String UPDATE_DRIVER_STATUS_EVENT= "update_driver_status_event";


    @KafkaListener(topics = ADD_CAB_EVENT, groupId = "driver-group")
    public void addCabEventDetails(String event) throws JsonProcessingException {
        CabEvent cabEvent = new ObjectMapper().readValue(event, CabEvent.class);
        LOGGER.info("CabEvent published to kafka with details "+cabEvent.toString());
       boolean isCabExistWithRegAndStatus=  cabService.verifyCabExistingWithRegistrationNumberAndStatus(cabEvent.getRegistrationNumber(), CommonStatusEnum.SUCCESS);
        CommonStatusEnum commonStatus = CommonStatusEnum.SUCCESS;
       if (isCabExistWithRegAndStatus){
            //Update the driver details with status failed
            commonStatus = CommonStatusEnum.FAILED;
        }
            //Update the driver details with status success
            saveCabDetailsAndUpdateDriverEvent(cabEvent, commonStatus);
    }

    private void saveCabDetailsAndUpdateDriverEvent(CabEvent cabEvent, CommonStatusEnum commonStatus) {
        Cab cab = saveCabDetails(cabEvent, commonStatus);
        updateDriverEvent(cab, commonStatus);
    }

    private void updateDriverEvent(Cab cab, CommonStatusEnum driverStatus) {
        UpdateDriverStatusEvent updateDriverStatusEvent = UpdateDriverStatusEvent.builder()
                .driverId(cab.getDriverId())
                .driverStatus(driverStatus).build();
        updateDriverStatusEventKafkaTemplate.send(UPDATE_DRIVER_STATUS_EVENT,updateDriverStatusEvent);
    }

    private Cab saveCabDetails(CabEvent cabEvent, CommonStatusEnum commonStatus) {
        return  cabService.addNewCab(Cab.builder()
                .driverId(cabEvent.getDriverId())
                .registrationNumber(cabEvent.getRegistrationNumber())
                .cabTypes(cabEvent.getCabTypes())
                .cabTypes(cabEvent.getCabTypes())
                .cabStatus(commonStatus)
                .build());
    }
}
