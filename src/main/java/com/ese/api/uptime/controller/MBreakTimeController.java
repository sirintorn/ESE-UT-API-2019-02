package com.ese.api.uptime.controller;

import com.ese.api.uptime.exception.ResourceNotFoundException;
import com.ese.api.uptime.model.MBreakTime;
import com.ese.api.uptime.repo.MBreakTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MBreakTimeController {
    @Autowired
    private MBreakTimeRepository mBreakTimeRepository;

    @RequestMapping("/BreakTimeAPI")
    @CrossOrigin(origins = "http://localhost:4200")
    public String getHello(@RequestParam(value = "name") String name){
        return "BreakTimeAPI Hello "+name+" !";
    }

    @PostMapping(value = "/postMBreakTime")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MBreakTime save(@RequestBody MBreakTime MBreakTime){
        return mBreakTimeRepository.save(MBreakTime);
    }

    @GetMapping(value = "/getMBreakTime")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<MBreakTime> all (Pageable pageable){
        return mBreakTimeRepository.findAll(pageable);
    }

    @GetMapping(value = "/getMBreakTime/{mBreakTimeId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public MBreakTime findByMBreakTimeId (@PathVariable Integer mBreakTimeId){
        return mBreakTimeRepository.findById(mBreakTimeId).orElseThrow(() -> new ResourceNotFoundException("mBreakTime [mBreakTimeId="+mBreakTimeId+"] can't be found"));
    }

    @DeleteMapping(value = "/delMBreakTime/{mBreakTimeId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteMBreakTime(@PathVariable Integer mBreakTimeId){

        return mBreakTimeRepository.findById(mBreakTimeId).map(mBreakTime -> {
            mBreakTimeRepository.delete(mBreakTime);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("mBreakTime [mBreakTimeId="+mBreakTimeId+"] can't be found"));

    }

    @PutMapping(value = "/putMBreakTime/{mBreakTimeId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<MBreakTime> updateMBreakTime(@PathVariable Integer mBreakTimeId,@RequestBody MBreakTime newMBreakTime){

        return mBreakTimeRepository.findById(mBreakTimeId).map(mBreakTime -> {
            mBreakTime.setId(newMBreakTime.getId());
            mBreakTime.setIsuse(newMBreakTime.getIsuse());
            mBreakTime.setStartTime(newMBreakTime.getStartTime());
            mBreakTime.setFinishTime(newMBreakTime.getFinishTime());
            mBreakTime.setFactoryId(newMBreakTime.getFactoryId());
            mBreakTime.setCreateDatetime(newMBreakTime.getCreateDatetime());
            mBreakTime.setCreateBy(newMBreakTime.getCreateBy());
            mBreakTime.setModifyBy(newMBreakTime.getModifyBy());
            mBreakTime.setModifyDatetime(newMBreakTime.getModifyDatetime());
            mBreakTime.setActive(newMBreakTime.getActive());
            mBreakTimeRepository.save(mBreakTime);
            return ResponseEntity.ok(mBreakTime);
        }).orElseThrow(() -> new ResourceNotFoundException("mBreakTime [mBreakTimeId="+mBreakTimeId+"] can't be found"));

    }
}
