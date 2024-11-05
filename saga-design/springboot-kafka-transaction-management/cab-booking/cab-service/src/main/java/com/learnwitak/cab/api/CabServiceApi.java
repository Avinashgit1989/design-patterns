package com.learnwitak.cab.api;

import com.learnwitak.cab.entity.Cab;
import com.learnwitak.cab.service.CabService;
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
@RequestMapping("/api/cab")
@AllArgsConstructor
public class CabServiceApi {
    public final static Logger LOGGER = LoggerFactory.getLogger(CabServiceApi.class);
    @Autowired
    private final CabService cabService;
    @PostMapping
    public ResponseEntity<Cab> addNewCab(@RequestBody Cab cab){
        LOGGER.info("Cab Service API to create New Cab  is called....");
        return new ResponseEntity<Cab>(cabService.addNewCab(cab), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cab>> getAllCab(){
        LOGGER.info("Cab Service API to get All Cab  is called....");
        return new ResponseEntity<List<Cab>>(cabService.getAllCab(), HttpStatus.OK);
    }

    @GetMapping("/{cabId}")
    public ResponseEntity<Optional<Cab>> getCabByCabId(UUID cabId){
        LOGGER.info("Cab Service API to get a Cab  is called....");
        return new ResponseEntity<Optional<Cab>>(cabService.getCabById(cabId), HttpStatus.OK);
    }

    @DeleteMapping("/{cabId}")
    public ResponseEntity<String> deleteByCabId(UUID cabId){
        LOGGER.info("Cab Service API to delete a Cab  is called....");
        cabService.deleteCabById(cabId);
        return new ResponseEntity<String>("Cab deleted Successfully.", HttpStatus.OK);
    }
}
