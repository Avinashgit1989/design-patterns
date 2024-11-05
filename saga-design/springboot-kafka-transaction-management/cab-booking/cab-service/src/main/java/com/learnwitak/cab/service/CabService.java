package com.learnwitak.cab.service;

import com.learnwitak.common.emuns.CommonStatusEnum;
import com.learnwitak.cab.entity.Cab;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CabService {


    List<Cab> getAllCab();

    Optional<Cab> getCabById(UUID cabId);

    Cab addNewCab(Cab cab);

    void deleteCabById(UUID cabId);

    boolean verifyCabExistingWithRegistrationNumberAndStatus(String registrationNumber, CommonStatusEnum success);
}
