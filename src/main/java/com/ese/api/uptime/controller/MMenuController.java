package com.ese.api.uptime.controller;

import com.ese.api.uptime.exception.ResourceNotFoundException;
import com.ese.api.uptime.model.MMenu;
import com.ese.api.uptime.repo.MMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class MMenuController {
    @Autowired
    private MMenuRepository mMenuRepository;

    @RequestMapping("/MenuAPI")
    @CrossOrigin(origins = "http://localhost:4200")
    public String getHello(@RequestParam(value = "name") String name){
        MMenu mm = new MMenu();
        mm.setSeq(1);
        mm.setMenuName(name);
        mm.setFactoryId(1);
//        mm.setCreateDatetime(new Date().toString());
        mm.setCreateBy(1);
        mm.setModifyBy(1);
//        mm.setModifyDatetime(new Date().toString());
        mm.setActive(true);
        save(mm);
        return "MenuAPI Hello "+name+" !";
    }

    @PostMapping(value = "/postMMenu")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MMenu save(@RequestBody MMenu mMenu){
        return mMenuRepository.save(mMenu);
    }

    @GetMapping(value = "/getMMenu")
    public Page<MMenu> all (Pageable pageable){
        return mMenuRepository.findAll(pageable);
    }

    @GetMapping(value = "/getMMenu/{mMenuId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public MMenu findByMMenuId (@PathVariable Integer mMenuId){
        return mMenuRepository.findById(mMenuId).orElseThrow(() -> new ResourceNotFoundException("MMenu [mMenuId="+mMenuId+"] can't be found"));
    }

    @DeleteMapping(value = "/delMMenu/{mMenuId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteMMenu(@PathVariable Integer mMenuId){

        return mMenuRepository.findById(mMenuId).map(mMenu -> {
            mMenuRepository.delete(mMenu);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("mMenu [mMenuId="+mMenuId+"] can't be found"));

    }

    @PutMapping(value = "/putMMenu/{mMenuId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<MMenu> updateMMenu(@PathVariable Integer mMenuId,@RequestBody MMenu newMenu){

        return mMenuRepository.findById(mMenuId).map(mMenu -> {
            mMenu.setId(newMenu.getId());
            mMenu.setSeq(newMenu.getSeq());
            mMenu.setMenuName(newMenu.getMenuName());
            mMenu.setFactoryId(newMenu.getFactoryId());
            mMenu.setCreateDatetime(newMenu.getCreateDatetime());
            mMenu.setCreateBy(newMenu.getCreateBy());
            mMenu.setModifyBy(newMenu.getModifyBy());
            mMenu.setModifyDatetime(newMenu.getModifyDatetime());
            mMenu.setActive(newMenu.getActive());

            mMenuRepository.save(newMenu);
            return ResponseEntity.ok(mMenu);
        }).orElseThrow(() -> new ResourceNotFoundException("MMenu [mMenuId="+mMenuId+"] can't be found"));

    }
}
