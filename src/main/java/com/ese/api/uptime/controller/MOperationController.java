package com.ese.api.uptime.controller;

import com.ese.api.uptime.exception.ResourceNotFoundException;
import com.ese.api.uptime.model.MOperation;
import com.ese.api.uptime.repo.MOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MOperationController {
    @Autowired
    private MOperationRepository mOperationRepository;

    @RequestMapping("/OperationAPI")
    @CrossOrigin(origins = "http://localhost:4200")
    public String getHello(@RequestParam(value = "name") String name){
        return "OperationAPI Hello "+name+" !";
    }

    @PostMapping(value = "/postMOperation")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MOperation save(@RequestBody MOperation MOperation){
        return mOperationRepository.save(MOperation);
    }

    @GetMapping(value = "/getMOperation")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<MOperation> all (Pageable pageable){
        return mOperationRepository.findAll(pageable);
    }

    @GetMapping(value = "/getMOperation/{mOperationId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public MOperation findByMOperationId (@PathVariable Integer mOperationId){
        return mOperationRepository.findById(mOperationId).orElseThrow(() -> new ResourceNotFoundException("mOperation [mOperationId="+mOperationId+"] can't be found"));
    }

    @DeleteMapping(value = "/delMOperation/{mOperationId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteMOperation(@PathVariable Integer mOperationId){

        return mOperationRepository.findById(mOperationId).map(mOperation -> {
            mOperationRepository.delete(mOperation);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("mOperation [mOperationId="+mOperationId+"] can't be found"));

    }

    @PutMapping(value = "/putMOperation/{mOperationId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<MOperation> updateMOperation(@PathVariable Integer mOperationId,@RequestBody MOperation newMOperation){

        return mOperationRepository.findById(mOperationId).map(mOperation -> {
            mOperation.setId(newMOperation.getId());
            mOperation.setOperationCode(newMOperation.getOperationCode());
            mOperation.setOperationName(newMOperation.getOperationName());
            mOperation.setParentId(newMOperation.getParentId());
            mOperation.setLevel(newMOperation.getLevel());
            mOperation.setMaxCapacity(newMOperation.getMaxCapacity());
            mOperation.setTakttime(newMOperation.getTakttime());
            mOperation.setLastStatus(newMOperation.getLastStatus());
            mOperation.setFactoryId(newMOperation.getFactoryId());
            mOperation.setCreateDatetime(newMOperation.getCreateDatetime());
            mOperation.setCreateBy(newMOperation.getCreateBy());
            mOperation.setModifyBy(newMOperation.getModifyBy());
            mOperation.setModifyDatetime(newMOperation.getModifyDatetime());
            mOperation.setActive(newMOperation.getActive());
            mOperationRepository.save(mOperation);
            return ResponseEntity.ok(mOperation);
        }).orElseThrow(() -> new ResourceNotFoundException("mOperation [mOperationId="+mOperationId+"] can't be found"));

    }
}
