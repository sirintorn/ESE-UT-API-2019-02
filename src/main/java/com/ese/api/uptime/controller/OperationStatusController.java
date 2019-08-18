package com.ese.api.uptime.controller;

import com.ese.api.uptime.exception.ResourceNotFoundException;
import com.ese.api.uptime.model.OperationStatus;
import com.ese.api.uptime.repo.OperationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OperationStatusController {
    @Autowired
    private OperationStatusRepository operationStatusRepository;

    @RequestMapping("/OperationStatusAPI")
    @CrossOrigin(origins = "http://localhost:4200")
    public String getHello(@RequestParam(value = "name") String name){

        return "OperationStatusAPI Hello "+name+" !";
    }

    @PostMapping(value = "/postOperationStatus")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(code = HttpStatus.CREATED)
    public OperationStatus save(@RequestBody OperationStatus operationStatus){
        return operationStatusRepository.save(operationStatus);
    }

    @GetMapping(value = "/getOperationStatus")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<OperationStatus> all (Pageable pageable){
        return operationStatusRepository.findAll(pageable);
    }

    @GetMapping(value = "/getOperationStatus/{operationStatusId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public OperationStatus findByOperationStatusId (@PathVariable Integer operationStatusId){
        return operationStatusRepository.findById(operationStatusId).orElseThrow(() -> new ResourceNotFoundException("OperationStatus [operationStatusId="+operationStatusId+"] can't be found"));
    }

    @DeleteMapping(value = "/delOperationStatus/{operationStatusId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteOperationStatus(@PathVariable Integer operationStatusId){

        return operationStatusRepository.findById(operationStatusId).map(operationStatus -> {
            operationStatusRepository.delete(operationStatus);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("OperationStatus [operationStatusId="+operationStatusId+"] can't be found"));

    }

    @PutMapping(value = "/putOperationStatus/{operationStatusId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<OperationStatus> updateOperationStatus(@PathVariable Integer operationStatusId,@RequestBody OperationStatus newOperationStatus){

        return operationStatusRepository.findById(operationStatusId).map(operationStatus -> {
            operationStatus.setId(newOperationStatus.getId());
            operationStatus.setFactoryId(newOperationStatus.getFactoryId());
            operationStatus.setOperationId(newOperationStatus.getOperationId());
            operationStatus.setStatusId(newOperationStatus.getStatusId());
            operationStatus.setActive(newOperationStatus.getActive());
            operationStatus.setSixbiglossId(newOperationStatus.getSixbiglossId());
            operationStatus.setEventDatetime(newOperationStatus.getEventDatetime());
            operationStatusRepository.save(operationStatus);
            return ResponseEntity.ok(operationStatus);
        }).orElseThrow(() -> new ResourceNotFoundException("OperationStatus [operationStatusId="+operationStatusId+"] can't be found"));

    }
}
