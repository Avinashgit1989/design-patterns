package com.learnwitak.common.driver.dto;

import com.learnwitak.common.cab.dto.CabDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DriverDto {
    private String driverName;
    private String driverEmail;
    private String driverLocation;
    private CabDto cabDto;
}
