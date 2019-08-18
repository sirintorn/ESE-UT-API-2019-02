package com.ese.api.uptime.controller;

import com.ese.api.uptime.exception.ResourceNotFoundException;
import com.ese.api.uptime.model.MShift;
import com.ese.api.uptime.repo.MShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MShiftController {
    @Autowired
    private MShiftRepository mShiftRepository;

    @RequestMapping("/ShiftAPI")
    @CrossOrigin(origins = "http://localhost:4200")
    public String getHello(@RequestParam(value = "name") String name){
        return "ShiftAPI Hello "+name+" !";
    }

    @PostMapping(value = "/postMShift")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MShift save(@RequestBody MShift mShift){
        return mShiftRepository.save(mShift);
    }

    @GetMapping(value = "/getMShift")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<MShift> all (Pageable pageable){
        return mShiftRepository.findAll(pageable);
    }

    @GetMapping(value = "/getMShift/{mShiftId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public MShift findByMShiftId (@PathVariable Integer mShiftId){
        return mShiftRepository.findById(mShiftId).orElseThrow(() -> new ResourceNotFoundException("MShift [mShiftId="+mShiftId+"] can't be found"));
    }

    @DeleteMapping(value = "/delMShift/{mShiftId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteMShift(@PathVariable Integer mShiftId){

        return mShiftRepository.findById(mShiftId).map(mShift -> {
            mShiftRepository.delete(mShift);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("MShift [mShiftId="+mShiftId+"] can't be found"));

    }

    @PutMapping(value = "/putMShift/{mShiftId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<MShift> updateMShift(@PathVariable Integer mShiftId,@RequestBody MShift newShift){

        return mShiftRepository.findById(mShiftId).map(mShift -> {
            mShift.setId(newShift.getId());
            mShift.setShiftName(newShift.getShiftName());
            mShift.setFactoryId(newShift.getFactoryId());
            mShift.setCreateDatetime(newShift.getCreateDatetime());
            mShift.setCreateBy(newShift.getCreateBy());
            mShift.setModifyBy(newShift.getModifyBy());
            mShift.setModifyDatetime(newShift.getModifyDatetime());
            mShift.setActive(newShift.getActive());
            mShiftRepository.save(mShift);
            return ResponseEntity.ok(mShift);
        }).orElseThrow(() -> new ResourceNotFoundException("MShift [mShiftId="+mShiftId+"] can't be found"));

    }
}
