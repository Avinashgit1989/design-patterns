package com.learnwitak.cab.service.impl;

import com.learnwitak.cab.repository.CabRepository;
import com.learnwitak.common.emuns.CommonStatusEnum;
import com.learnwitak.cab.entity.Cab;
import com.learnwitak.cab.service.CabService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CabServiceImpl implements CabService {
    public final static Logger LOGGER = LoggerFactory.getLogger(CabServiceImpl.class);
    @Autowired
    private final CabRepository cabRepository;

    @Override
    public List<Cab> getAllCab() {
        LOGGER.info("Get All Cab Service calling..");
        return cabRepository.findAll();
    }

    @Override
    public Optional<Cab> getCabById(UUID cabId) {
        LOGGER.info("Get Cab By Id Service calling..");
        return cabRepository.findById(cabId);
    }

    @Override
    public Cab addNewCab(Cab cab) {
        LOGGER.info("Add new Cab Service calling..");
        return cabRepository.save(cab);
    }

    @Override
    public void deleteCabById(UUID cabId) {
        LOGGER.info("Delete Cab By Id Service calling..");
         cabRepository.deleteById(cabId);
    }

    @Override
    public boolean verifyCabExistingWithRegistrationNumberAndStatus(String registrationNumber, CommonStatusEnum success) {
        return cabRepository.existsByRegistrationNumberAndCabStatus(registrationNumber, success);
    }
}
