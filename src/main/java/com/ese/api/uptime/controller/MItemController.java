package com.ese.api.uptime.controller;

import com.ese.api.uptime.exception.ResourceNotFoundException;
import com.ese.api.uptime.model.MItem;
import com.ese.api.uptime.repo.MItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MItemController {
    @Autowired
    private MItemRepository mItemRepository;

    @RequestMapping("/ItemAPI")
    @CrossOrigin(origins = "http://localhost:4200")
    public String getHello(@RequestParam(value = "name") String name){
        return "ItemAPI Hello "+name+" !";
    }

    @PostMapping(value = "/postMItem")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MItem save(@RequestBody MItem MItem){
        return mItemRepository.save(MItem);
    }

    @GetMapping(value = "/getMItem")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<MItem> all (Pageable pageable){
        return mItemRepository.findAll(pageable);
    }

    @GetMapping(value = "/getMItem/{mItemId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public MItem findByMItemId (@PathVariable Integer mItemId){
        return mItemRepository.findById(mItemId).orElseThrow(() -> new ResourceNotFoundException("mItem [mItemId="+mItemId+"] can't be found"));
    }

    @DeleteMapping(value = "/delMItem/{mItemId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteMItem(@PathVariable Integer mItemId){

        return mItemRepository.findById(mItemId).map(mItem -> {
            mItemRepository.delete(mItem);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("mItem [mItemId="+mItemId+"] can't be found"));

    }

    @PutMapping(value = "/putMItem/{mItemId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<MItem> updateMItem(@PathVariable Integer mItemId,@RequestBody MItem newMItem){

        return mItemRepository.findById(mItemId).map(mItem -> {
            mItem.setId(newMItem.getId());
            mItem.setPartNumber(newMItem.getPartNumber());
            mItem.setPartName(newMItem.getPartName());
            mItem.setPartnerId(newMItem.getPartnerId());
            mItem.setDescription1(newMItem.getDescription1());
            mItem.setDescription2(newMItem.getDescription2());
            mItem.setDescription3(newMItem.getDescription3());
            mItem.setType(newMItem.getType());
            mItem.setFactoryId(newMItem.getFactoryId());
            mItem.setCreateDatetime(newMItem.getCreateDatetime());
            mItem.setCreateBy(newMItem.getCreateBy());
            mItem.setModifyBy(newMItem.getModifyBy());
            mItem.setModifyDatetime(newMItem.getModifyDatetime());
            mItem.setActive(newMItem.getActive());
            mItemRepository.save(mItem);
            return ResponseEntity.ok(mItem);
        }).orElseThrow(() -> new ResourceNotFoundException("mItem [mItemId="+mItemId+"] can't be found"));

    }
}
