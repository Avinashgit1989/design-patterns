package com.learnwitak.common.cab.events;

import com.learnwitak.common.cab.types.CabTypes;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CabEvent {
    private UUID driverId;
    private String registrationNumber;
    private CabTypes cabTypes;
}
