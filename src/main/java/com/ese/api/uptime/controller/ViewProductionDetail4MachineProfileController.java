package com.ese.api.uptime.controller;

import com.ese.api.uptime.exception.ResourceNotFoundException;
import com.ese.api.uptime.model.ViewProductionDetail4MachineProfile;
import com.ese.api.uptime.repo.ViewProductionDetail4MachineProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class ViewProductionDetail4MachineProfileController {
    @Autowired
    private ViewProductionDetail4MachineProfileRepository productionDetailRepository;


    @GetMapping(value = "/getProductionDetail4MachineProfile")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<ViewProductionDetail4MachineProfile> all (Pageable pageable){
        return productionDetailRepository.findAll(pageable);
    }

    @GetMapping(value = "/getProductionDetail4MachineProfile/{productionDetailId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ViewProductionDetail4MachineProfile findByViewProductionDetail4MachineProfileId (@PathVariable Integer productionDetailId){
        return productionDetailRepository.findById(productionDetailId).orElseThrow(() -> new ResourceNotFoundException("ProductionDetail [productionDetailId="+productionDetailId+"] can't be found"));
    }



}
