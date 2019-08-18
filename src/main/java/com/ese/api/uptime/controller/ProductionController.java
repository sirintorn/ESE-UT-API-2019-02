package com.ese.api.uptime.controller;

import com.ese.api.uptime.exception.ResourceNotFoundException;
import com.ese.api.uptime.model.Production;
import com.ese.api.uptime.repo.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductionController {
    @Autowired
    private ProductionRepository productionRepository;

    @RequestMapping("/ProductionAPI")
    @CrossOrigin(origins = "http://localhost:4200")
    public String getHello(@RequestParam(value = "name") String name){
        return "ProductionAPI Hello "+name+" !";
    }

    @PostMapping(value = "/postProduction")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Production save(@RequestBody Production Production){
        return productionRepository.save(Production);
    }

    @GetMapping(value = "/getProduction")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<Production> all (Pageable pageable){
        return productionRepository.findAll(pageable);
    }

    @GetMapping(value = "/getProduction/{productionId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Production findByProductionId (@PathVariable Integer productionId){
        return productionRepository.findById(productionId).orElseThrow(() -> new ResourceNotFoundException("Production [productionId="+productionId+"] can't be found"));
    }

    @GetMapping(value = "/getProductionByMachineReg/{machineRegisterId}/{factoryId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Production> findByMachineRegisterId (@PathVariable Integer machineRegisterId,@PathVariable Integer factoryId){
        return productionRepository.findByMachineRegisterId(machineRegisterId,factoryId);


    }

    @DeleteMapping(value = "/delProduction/{productionId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteProduction(@PathVariable Integer productionId){

        return productionRepository.findById(productionId).map(production -> {
            productionRepository.delete(production);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Production [productionId="+productionId+"] can't be found"));

    }

    @PutMapping(value = "/putProduction/{productionId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Production> updateProduction(@PathVariable Integer productionId,@RequestBody Production newProduction){

        return productionRepository.findById(productionId).map(production -> {
            production.setId(newProduction.getId());
            production.setDocno(newProduction.getDocno());
            production.setShiftId(newProduction.getShiftId());
            production.setProduct(newProduction.getProduct());
            production.setTarget(newProduction.getTarget());
            production.setTakttime(newProduction.getTakttime());
            production.setShiftId(newProduction.getShiftId());
            production.setPlanstart(newProduction.getPlanstart());
            production.setPlanfinish(newProduction.getPlanfinish());
            production.setBreak1start(newProduction.getBreak1start());
            production.setBreak1finish(newProduction.getBreak1finish());
            production.setBreak2start(newProduction.getBreak2start());
            production.setBreak2finish(newProduction.getBreak2finish());
            production.setBreak3start(newProduction.getBreak3start());
            production.setBreak3finish(newProduction.getBreak3finish());
            production.setActualstart(newProduction.getActualstart());
            production.setActualfinish(newProduction.getActualfinish());
            production.setTotaldowntime(newProduction.getTotaldowntime());
            production.setBalance(newProduction.getBalance());
            production.setMachineRegisterId(newProduction.getMachineRegisterId());
            production.setStatusId(newProduction.getStatusId());
            production.setOperationId(newProduction.getOperationId());

            production.setFactoryId(newProduction.getFactoryId());
            production.setCreateDatetime(newProduction.getCreateDatetime());
            production.setCreateBy(newProduction.getCreateBy());
            production.setModifyBy(newProduction.getModifyBy());
            production.setModifyDatetime(newProduction.getModifyDatetime());
            production.setActive(newProduction.getActive());
            productionRepository.save(production);
            return ResponseEntity.ok(production);
        }).orElseThrow(() -> new ResourceNotFoundException("Production [productionId="+productionId+"] can't be found"));

    }
}
