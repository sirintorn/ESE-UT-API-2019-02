package com.ese.api.uptime.controller;

import com.ese.api.uptime.exception.ResourceNotFoundException;
import com.ese.api.uptime.model.MOeeloss;
import com.ese.api.uptime.repo.MOeelossRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MOeeLossController {
    @Autowired
    private MOeelossRepository mOeelossRepository;

    @RequestMapping("/OeelossAPI")
    @CrossOrigin(origins = "http://localhost:4200")
    public String getHello(@RequestParam(value = "name") String name){
        return "OeelossAPI Hello "+name+" !";
    }

    @PostMapping(value = "/postMOeeloss")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MOeeloss save(@RequestBody MOeeloss mOeeloss){
        return mOeelossRepository.save(mOeeloss);
    }

    @GetMapping(value = "/getMOeeloss")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<MOeeloss> all (Pageable pageable){
        return mOeelossRepository.findAll(pageable);
    }

    @GetMapping(value = "/getMOeeloss/{mOeelossId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public MOeeloss findByMOeelossId (@PathVariable Integer mOeelossId){
        return mOeelossRepository.findById(mOeelossId).orElseThrow(() -> new ResourceNotFoundException("mOeeloss [mOeelossId="+mOeelossId+"] can't be found"));
    }

    @DeleteMapping(value = "/delMOeeloss/{mOeelossId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteMOeeloss(@PathVariable Integer mOeelossId){

        return mOeelossRepository.findById(mOeelossId).map(mOeeloss -> {
            mOeelossRepository.delete(mOeeloss);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("MOeeloss [MOeelossId="+mOeelossId+"] can't be found"));

    }

    @PutMapping(value = "/putMOeeloss/{mOeelossId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<MOeeloss> updateMOeeloss(@PathVariable Integer mOeelossId,@RequestBody MOeeloss newOeeloss){

        return mOeelossRepository.findById(mOeelossId).map(mOeeloss -> {
            mOeeloss.setId(newOeeloss.getId());
            mOeeloss.setLossType(newOeeloss.getLossType());
            mOeeloss.setFactoryId(newOeeloss.getFactoryId());
            mOeeloss.setCreateDatetime(newOeeloss.getCreateDatetime());
            mOeeloss.setCreateBy(newOeeloss.getCreateBy());
            mOeeloss.setModifyBy(newOeeloss.getModifyBy());
            mOeeloss.setModifyDatetime(newOeeloss.getModifyDatetime());
            mOeeloss.setActive(newOeeloss.getActive());
            mOeelossRepository.save(mOeeloss);
            return ResponseEntity.ok(mOeeloss);
        }).orElseThrow(() -> new ResourceNotFoundException("MOeeloss [mOeelossId="+mOeelossId+"] can't be found"));

    }
}
