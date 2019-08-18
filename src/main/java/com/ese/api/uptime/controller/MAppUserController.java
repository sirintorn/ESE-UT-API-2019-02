package com.ese.api.uptime.controller;

import com.ese.api.uptime.exception.ResourceNotFoundException;
import com.ese.api.uptime.model.MAppUser;
import com.ese.api.uptime.repo.MAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MAppUserController {
    @Autowired
    private MAppUserRepository mAppUserRepository;

    @RequestMapping("/AppUserAPI")
    @CrossOrigin(origins = "http://localhost:4200")
    public String getHello(@RequestParam(value = "name") String name){
        return "AppUserAPI Hello "+name+" !";
    }

    @PostMapping(value = "/postMAppUser")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MAppUser save(@RequestBody MAppUser MAppUser){
        return mAppUserRepository.save(MAppUser);
    }

    @GetMapping(value = "/getMAppUser")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<MAppUser> all (Pageable pageable){
        return mAppUserRepository.findAll(pageable);
    }

    @GetMapping(value = "/getMAppUser/{mAppUserId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public MAppUser findByMAppUserId (@PathVariable Integer mAppUserId){
        return mAppUserRepository.findById(mAppUserId).orElseThrow(() -> new ResourceNotFoundException("mAppUser [mAppUserId="+mAppUserId+"] can't be found"));
    }

    @DeleteMapping(value = "/delMAppUser/{mAppUserId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteMAppUser(@PathVariable Integer mAppUserId){

        return mAppUserRepository.findById(mAppUserId).map(mAppUser -> {
            mAppUserRepository.delete(mAppUser);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("mAppUser [mAppUserId="+mAppUserId+"] can't be found"));

    }

    @PutMapping(value = "/putMAppUser/{mAppUserId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<MAppUser> updateMAppUser(@PathVariable Integer mAppUserId,@RequestBody MAppUser newMAppUser){

        return mAppUserRepository.findById(mAppUserId).map(mAppUser -> {
            mAppUser.setId(newMAppUser.getId());
            mAppUser.setName(newMAppUser.getName());
            mAppUser.setUsername(newMAppUser.getUsername());
            mAppUser.setPassword(newMAppUser.getPassword());
            mAppUser.setRefNo(newMAppUser.getRefNo());
            mAppUser.setEmail(newMAppUser.getEmail());
            mAppUser.setLogin(newMAppUser.getLogin());
            mAppUser.setFactoryId(newMAppUser.getFactoryId());
            mAppUser.setCreateBy(newMAppUser.getCreateBy());
            mAppUser.setModifyBy(newMAppUser.getModifyBy());
            mAppUser.setModifyDate(newMAppUser.getModifyDate());
            mAppUser.setActive(newMAppUser.getActive());
            mAppUserRepository.save(mAppUser);
            return ResponseEntity.ok(mAppUser);
        }).orElseThrow(() -> new ResourceNotFoundException("mAppUser [mAppUserId="+mAppUserId+"] can't be found"));

    }
}
