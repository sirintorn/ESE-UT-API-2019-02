package com.ese.api.uptime.controller;

import com.ese.api.uptime.exception.ResourceNotFoundException;
import com.ese.api.uptime.model.MMachineRegister;
import com.ese.api.uptime.repo.MMachineRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MMachineRegisterController {
    @Autowired
    private MMachineRegisterRepository mMachineRegisterRepository;

    @RequestMapping("/MachineRegisterAPI")
    @CrossOrigin(origins = "http://localhost:4200")
    public String getHello(@RequestParam(value = "name") String name){
        return "MachineRegisterAPI Hello "+name+" !";
    }


    @PostMapping(value = "/postMMachineRegister")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MMachineRegister save(@RequestBody MMachineRegister mMachineRegister){
        return mMachineRegisterRepository.save(mMachineRegister);
    }

    @GetMapping(value = "/getMMachineRegister")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<MMachineRegister> all (Pageable pageable){
        return mMachineRegisterRepository.findAll(pageable);
    }

    @GetMapping(value = "/getMMachineRegister/{mMachineRegisterId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public MMachineRegister findByMMachineRegisterId (@PathVariable Integer mMachineRegisterId){
        return mMachineRegisterRepository.findById(mMachineRegisterId).orElseThrow(() -> new ResourceNotFoundException("MMachineRegister [mMachineRegisterId="+mMachineRegisterId+"] can't be found"));
    }

    @DeleteMapping(value = "/delMMachineRegister/{mMachineRegisterId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteMMachineRegisterId(@PathVariable Integer mMachineRegisterId){

        return mMachineRegisterRepository.findById(mMachineRegisterId).map(mMachineRegister -> {
            mMachineRegisterRepository.delete(mMachineRegister);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("mMachineRegister [mMachineRegisterId="+mMachineRegisterId+"] can't be found"));

    }

    @PutMapping(value = "/putMMachineRegister/{mMachineRegisterId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<MMachineRegister> updateMMachineRegister(@PathVariable Integer mMachineRegisterId,@RequestBody MMachineRegister newMMachineRegister){

        return mMachineRegisterRepository.findById(mMachineRegisterId).map(mMachineRegister -> {
            mMachineRegister.setId(newMMachineRegister.getId());
            mMachineRegister.setSerialNo(newMMachineRegister.getSerialNo());
            mMachineRegister.setFactoryId(newMMachineRegister.getFactoryId());
            mMachineRegister.setCreateDatetime(newMMachineRegister.getCreateDatetime());
            mMachineRegister.setCreateBy(newMMachineRegister.getCreateBy());
            mMachineRegister.setModifyBy(newMMachineRegister.getModifyBy());
            mMachineRegister.setModifyDatetime(newMMachineRegister.getModifyDatetime());
            mMachineRegister.setActive(newMMachineRegister.getActive());
            mMachineRegisterRepository.save(mMachineRegister);
            return ResponseEntity.ok(mMachineRegister);
        }).orElseThrow(() -> new ResourceNotFoundException("MMachineRegister [mMachineRegisterId="+mMachineRegisterId+"] can't be found"));

    }
}
