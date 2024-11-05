package com.learnwitak.common.cab.dto;

import com.learnwitak.common.cab.types.CabTypes;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CabDto {
    private String registrationNumber;
    private CabTypes cabType;

}
