package com.ese.api.uptime.controller;

import com.ese.api.uptime.exception.ResourceNotFoundException;
import com.ese.api.uptime.model.MSixbigloss;
import com.ese.api.uptime.repo.MSixbiglossRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MSixBiglossController {
    @Autowired
    private MSixbiglossRepository mSixbiglossRepository;

    @RequestMapping("/SixlossAPI")
    @CrossOrigin(origins = "http://localhost:4200")
    public String getHello(@RequestParam(value = "name") String name){
        return "SixlossAPI Hello "+name+" !";
    }

    @PostMapping(value = "/postMSixloss")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MSixbigloss save(@RequestBody MSixbigloss mSixloss){
        return mSixbiglossRepository.save(mSixloss);
    }

    @GetMapping(value = "/getMSixloss")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<MSixbigloss> all (Pageable pageable){
        return mSixbiglossRepository.findAll(pageable);
    }

    @GetMapping(value = "/getMSixbigloss/{mSixbiglossId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public MSixbigloss findByMSixbiglossId (@PathVariable Integer mSixbiglossId){
        return mSixbiglossRepository.findById(mSixbiglossId).orElseThrow(() -> new ResourceNotFoundException("MSixbigloss [SixbiglossId="+mSixbiglossId+"] can't be found"));
    }

    @DeleteMapping(value = "/delMSixbigloss/{mSixbiglossId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteMSixbigloss(@PathVariable Integer mSixbiglossId){

        return mSixbiglossRepository.findById(mSixbiglossId).map(mSixbigloss -> {
            mSixbiglossRepository.delete(mSixbigloss);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("MSixbigloss [mSixlossId="+mSixbiglossId+"] can't be found"));

    }

    @PutMapping(value = "/putMSixbigloss/{mSixbiglossId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<MSixbigloss> updateMSixbigloss(@PathVariable Integer mSixbiglossId,@RequestBody MSixbigloss newSixbigloss){

        return mSixbiglossRepository.findById(mSixbiglossId).map(mSixbigloss -> {
            mSixbigloss.setId(newSixbigloss.getId());
            mSixbigloss.setSixbiglossCategoryId(newSixbigloss.getSixbiglossCategoryId());
            mSixbigloss.setCode(newSixbigloss.getCode());
            mSixbigloss.setSixbiglossName(newSixbigloss.getSixbiglossName());
            mSixbigloss.setFactoryId(newSixbigloss.getFactoryId());
            mSixbigloss.setCreateDatetime(newSixbigloss.getCreateDatetime());
            mSixbigloss.setCreateBy(newSixbigloss.getCreateBy());
            mSixbigloss.setModifyBy(newSixbigloss.getModifyBy());
            mSixbigloss.setModifyDatetime(newSixbigloss.getModifyDatetime());
            mSixbigloss.setActive(newSixbigloss.getActive());
            mSixbiglossRepository.save(mSixbigloss);
            return ResponseEntity.ok(mSixbigloss);
        }).orElseThrow(() -> new ResourceNotFoundException("MSixbigloss [mSixbiglossId="+mSixbiglossId+"] can't be found"));

    }
}
