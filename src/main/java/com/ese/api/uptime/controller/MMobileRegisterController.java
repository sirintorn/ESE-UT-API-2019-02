package com.ese.api.uptime.controller;

import com.ese.api.uptime.exception.ResourceNotFoundException;
import com.ese.api.uptime.model.MMobileRegister;
import com.ese.api.uptime.repo.MMobileRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MMobileRegisterController {
    @Autowired
    private MMobileRegisterRepository mMobileRegisterRepository;

    @RequestMapping("/MobileRegisterAPI")
    @CrossOrigin(origins = "http://localhost:4200")
    public String getHello(@RequestParam(value = "name") String name){
        return "MobileRegisterAPI Hello "+name+" !";
    }

    @PostMapping(value = "/postMMobileRegister")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MMobileRegister save(@RequestBody MMobileRegister MMobileRegister){
        return mMobileRegisterRepository.save(MMobileRegister);
    }

    @GetMapping(value = "/getMMobileRegister")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<MMobileRegister> all (Pageable pageable){
        return mMobileRegisterRepository.findAll(pageable);
    }

    @GetMapping(value = "/getMMobileRegister/{mMobileRegisterId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public MMobileRegister findByMMobileRegisterId (@PathVariable Integer mMobileRegisterId){
        return mMobileRegisterRepository.findById(mMobileRegisterId).orElseThrow(() -> new ResourceNotFoundException("mMobileRegister [mMobileRegisterId="+mMobileRegisterId+"] can't be found"));
    }

    @DeleteMapping(value = "/delMMobileRegister/{mMobileRegisterId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteMMobileRegister(@PathVariable Integer mMobileRegisterId){

        return mMobileRegisterRepository.findById(mMobileRegisterId).map(mMobileRegister -> {
            mMobileRegisterRepository.delete(mMobileRegister);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("mMobileRegister [mMobileRegisterId="+mMobileRegisterId+"] can't be found"));

    }

    @PutMapping(value = "/putMMobileRegister/{mMobileRegisterId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<MMobileRegister> updateMMobileRegister(@PathVariable Integer mMobileRegisterId,@RequestBody MMobileRegister newMMobileRegister){

        return mMobileRegisterRepository.findById(mMobileRegisterId).map(mMobileRegister -> {
            mMobileRegister.setId(newMMobileRegister.getId());
            mMobileRegister.setSerialNo(newMMobileRegister.getSerialNo());
            mMobileRegister.setFactoryId(newMMobileRegister.getFactoryId());
            mMobileRegister.setCreateDatetime(newMMobileRegister.getCreateDatetime());
            mMobileRegister.setCreateBy(newMMobileRegister.getCreateBy());
            mMobileRegister.setModifyBy(newMMobileRegister.getModifyBy());
            mMobileRegister.setModifyDatetime(newMMobileRegister.getModifyDatetime());
            mMobileRegister.setActive(newMMobileRegister.getActive());
            mMobileRegisterRepository.save(mMobileRegister);
            return ResponseEntity.ok(mMobileRegister);
        }).orElseThrow(() -> new ResourceNotFoundException("mMobileRegister [mMobileRegisterId="+mMobileRegisterId+"] can't be found"));

    }
}
