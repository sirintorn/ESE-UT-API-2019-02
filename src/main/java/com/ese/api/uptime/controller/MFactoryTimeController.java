package com.ese.api.uptime.controller;

import com.ese.api.uptime.exception.ResourceNotFoundException;
import com.ese.api.uptime.model.MFactory;
import com.ese.api.uptime.repo.MFactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MFactoryTimeController {
    @Autowired
    private MFactoryRepository mFactoryRepository;

    @RequestMapping("/FactoryAPI")
    @CrossOrigin(origins = "http://localhost:4200")
    public String getHello(@RequestParam(value = "name") String name){
        return "FactoryAPI Hello "+name+" !";
    }

    @PostMapping(value = "/postMFactory")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MFactory save(@RequestBody MFactory MFactory){
        return mFactoryRepository.save(MFactory);
    }

    @GetMapping(value = "/getMFactory")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<MFactory> all (Pageable pageable){
        return mFactoryRepository.findAll(pageable);
    }

    @GetMapping(value = "/getMFactory/{mFactoryId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public MFactory findByMFactoryId (@PathVariable Integer mFactoryId){
        return mFactoryRepository.findById(mFactoryId).orElseThrow(() -> new ResourceNotFoundException("mFactory [mFactoryId="+mFactoryId+"] can't be found"));
    }

    @DeleteMapping(value = "/delMFactory/{mFactoryId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteMFactory(@PathVariable Integer mFactoryId){

        return mFactoryRepository.findById(mFactoryId).map(mFactory -> {
            mFactoryRepository.delete(mFactory);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("mFactory [mFactoryId="+mFactoryId+"] can't be found"));

    }

    @PutMapping(value = "/putMFactory/{mFactoryId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<MFactory> updateMFactory(@PathVariable Integer mFactoryId,@RequestBody MFactory newMFactory){

        return mFactoryRepository.findById(mFactoryId).map(mFactory -> {
            mFactory.setId(newMFactory.getId());
            mFactory.setName(newMFactory.getName());
            mFactory.setAddress(newMFactory.getAddress());
            mFactory.setPhone(newMFactory.getPhone());
            mFactory.setAdminId(newMFactory.getAdminId());
            mFactory.setCreateDatetime(newMFactory.getCreateDatetime());
            mFactory.setCreateBy(newMFactory.getCreateBy());
            mFactory.setModifyBy(newMFactory.getModifyBy());
            mFactory.setModifyDatetime(newMFactory.getModifyDatetime());
            mFactory.setActive(newMFactory.getActive());
            mFactoryRepository.save(mFactory);
            return ResponseEntity.ok(mFactory);
        }).orElseThrow(() -> new ResourceNotFoundException("mFactory [mFactoryId="+mFactoryId+"] can't be found"));

    }
}
