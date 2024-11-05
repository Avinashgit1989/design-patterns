package com.learnwitak.common.driver.event;

import com.learnwitak.common.emuns.CommonStatusEnum;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateDriverStatusEvent {

    private UUID driverId;
    private CommonStatusEnum driverStatus;
}
