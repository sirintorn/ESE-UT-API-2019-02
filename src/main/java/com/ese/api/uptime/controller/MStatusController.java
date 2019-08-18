package com.ese.api.uptime.controller;

import com.ese.api.uptime.exception.ResourceNotFoundException;
import com.ese.api.uptime.model.MStatus;
import com.ese.api.uptime.repo.MStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MStatusController {
    @Autowired
    private MStatusRepository mStatusRepository;

    @RequestMapping("/StatusAPI")
    @CrossOrigin(origins = "http://localhost:4200")
    public String getHello(@RequestParam(value = "name") String name){
        return "StatusAPI Hello "+name+" !";
    }

    @PostMapping(value = "/postMStatus")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MStatus save(@RequestBody MStatus MStatus){
        return mStatusRepository.save(MStatus);
    }

    @GetMapping(value = "/getMStatus")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<MStatus> all (Pageable pageable){
        return mStatusRepository.findAll(pageable);
    }

    @GetMapping(value = "/getMStatus/{mStatusId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public MStatus findByMStatusId (@PathVariable Integer mStatusId){
        return mStatusRepository.findById(mStatusId).orElseThrow(() -> new ResourceNotFoundException("mStatus [mStatusId="+mStatusId+"] can't be found"));
    }

    @DeleteMapping(value = "/delMStatus/{mStatusId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteMStatus(@PathVariable Integer mStatusId){

        return mStatusRepository.findById(mStatusId).map(mStatus -> {
            mStatusRepository.delete(mStatus);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("mStatus [mStatusId="+mStatusId+"] can't be found"));

    }

    @PutMapping(value = "/putMStatus/{mStatusId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<MStatus> updateMStatus(@PathVariable Integer mStatusId,@RequestBody MStatus newMStatus){

        return mStatusRepository.findById(mStatusId).map(mStatus -> {
            mStatus.setId(newMStatus.getId());
            mStatus.setStatusName(newMStatus.getStatusName());
            mStatus.setDocStatus(newMStatus.getDocStatus());
            mStatus.setOperationStatus(newMStatus.getOperationStatus());
            mStatus.setFactoryId(newMStatus.getFactoryId());
            mStatus.setCreateDatetime(newMStatus.getCreateDatetime());
            mStatus.setCreateBy(newMStatus.getCreateBy());
            mStatus.setModifyBy(newMStatus.getModifyBy());
            mStatus.setModifyDatetime(newMStatus.getModifyDatetime());
            mStatus.setActive(newMStatus.getActive());
            mStatusRepository.save(mStatus);
            return ResponseEntity.ok(mStatus);
        }).orElseThrow(() -> new ResourceNotFoundException("mStatus [mStatusId="+mStatusId+"] can't be found"));

    }
}
