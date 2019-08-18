package com.ese.api.uptime.controller;

import com.ese.api.uptime.exception.ResourceNotFoundException;
import com.ese.api.uptime.model.MSixbiglossCategory;
import com.ese.api.uptime.repo.MSixbiglossCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MSixBiglossCategoryController {
    @Autowired
    private MSixbiglossCategoryRepository mSixbiglossCategoryRepository;

    @RequestMapping("/SixbiglossCategoryAPI")
    @CrossOrigin(origins = "http://localhost:4200")
    public String getHello(@RequestParam(value = "name") String name){
        return "SixbiglossCategoryAPI Hello "+name+" !";
    }

    @PostMapping(value = "/postMSixlossCategory")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MSixbiglossCategory save(@RequestBody MSixbiglossCategory mSixbiglossCategory){
        return mSixbiglossCategoryRepository.save(mSixbiglossCategory);
    }

    @GetMapping(value = "/getMSixlossCategory")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<MSixbiglossCategory> all (Pageable pageable){
        return mSixbiglossCategoryRepository.findAll(pageable);
    }

    @GetMapping(value = "/getMSixlossCategory/{mSixlossCategoryId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public MSixbiglossCategory findByMSixbiglossCategoryId (@PathVariable Integer mSixbiglossCategoryId){
        return mSixbiglossCategoryRepository.findById(mSixbiglossCategoryId).orElseThrow(() -> new ResourceNotFoundException("MSixbiglossCategory [SixbiglossCategoryId="+mSixbiglossCategoryId+"] can't be found"));
    }

    @DeleteMapping(value = "/delMSixlossCategory/{mSixbiglossCategoryId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteMSixbiglossCategory(@PathVariable Integer mSixbiglossCategoryId){

        return mSixbiglossCategoryRepository.findById(mSixbiglossCategoryId).map(mSixbiglossCategory -> {
            mSixbiglossCategoryRepository.delete(mSixbiglossCategory);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("mSixbiglossCategory [mSixlossCategoryId="+mSixbiglossCategoryId+"] can't be found"));

    }

    @PutMapping(value = "/putMSixlossCategory/{mSixbiglossCategoryId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<MSixbiglossCategory> updateMSixbiglossCategory(@PathVariable Integer mSixbiglossCategoryId,@RequestBody MSixbiglossCategory newSixbiglossCategory){

        return mSixbiglossCategoryRepository.findById(mSixbiglossCategoryId).map(mSixbiglossCategory -> {
            mSixbiglossCategory.setId(newSixbiglossCategory.getId());
            mSixbiglossCategory.setSixbiglossCategoryName(newSixbiglossCategory.getSixbiglossCategoryName());
            mSixbiglossCategory.setFactoryId(newSixbiglossCategory.getFactoryId());
            mSixbiglossCategory.setCreateDatetime(newSixbiglossCategory.getCreateDatetime());
            mSixbiglossCategory.setCreateBy(newSixbiglossCategory.getCreateBy());
            mSixbiglossCategory.setModifyBy(newSixbiglossCategory.getModifyBy());
            mSixbiglossCategory.setModifyDatetime(newSixbiglossCategory.getModifyDatetime());
            mSixbiglossCategory.setActive(newSixbiglossCategory.getActive());
            mSixbiglossCategoryRepository.save(mSixbiglossCategory);
            return ResponseEntity.ok(mSixbiglossCategory);
        }).orElseThrow(() -> new ResourceNotFoundException("MSixbiglossCategory [mSixbiglossCategoryId="+mSixbiglossCategoryId+"] can't be found"));

    }
}
