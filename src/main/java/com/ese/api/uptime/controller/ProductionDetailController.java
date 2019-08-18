package com.ese.api.uptime.controller;

import com.ese.api.uptime.exception.ResourceNotFoundException;
import com.ese.api.uptime.model.ProductionDetail;
import com.ese.api.uptime.repo.ProductionDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductionDetailController {
    @Autowired
    private ProductionDetailRepository productionDetailRepository;

    @RequestMapping("/ProductionDetailAPI")
    @CrossOrigin(origins = "http://localhost:4200")
    public String getHello(@RequestParam(value = "productionId") String productionId,
                           @RequestParam(value = "productionEvent") String productionEvent,
                           @RequestParam(value = "machineRegisterId") String machineRegisterId,
                           @RequestParam(value = "totaltime") String totaltime,
                           @RequestParam(value = "lossId") String lostId,
                           @RequestParam(value = "factoryId")String factoryId){

        ProductionDetail pd = new ProductionDetail();
        pd.setProductionId(Integer.parseInt(productionId));
        pd.setProductionEvent(productionEvent);
        pd.setMachineRegisterId(Integer.parseInt(machineRegisterId));
        pd.setTotaltime(Integer.parseInt(totaltime));
        pd.setLossId(Integer.parseInt(lostId));
        pd.setFactoryId(Integer.parseInt(factoryId));
        save(pd);
        return "productionDetailAPI Hello "+productionId+" !";
    }

    @PostMapping(value = "/postProductionDetail")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProductionDetail save(@RequestBody ProductionDetail ProductionDetail){
        return productionDetailRepository.save(ProductionDetail);
    }

    @GetMapping(value = "/getProductionDetail")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<ProductionDetail> all (Pageable pageable){
        return productionDetailRepository.findAll(pageable);
    }

    @GetMapping(value = "/getProductionDetail/{productionDetailId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ProductionDetail findByProductionDetailId (@PathVariable Integer productionDetailId){
        return productionDetailRepository.findById(productionDetailId).orElseThrow(() -> new ResourceNotFoundException("ProductionDetail [productionDetailId="+productionDetailId+"] can't be found"));
    }


    @DeleteMapping(value = "/delProductionDetail/{productionDetailId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteProductionDetail(@PathVariable Integer productionDetailId){

        return productionDetailRepository.findById(productionDetailId).map(productionDetail -> {
            productionDetailRepository.delete(productionDetail);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("ProductionDetail [productionDetailId="+productionDetailId+"] can't be found"));

    }

    @PutMapping(value = "/putProductionDetail/{productionDetailId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ProductionDetail> updateProductionDetail(@PathVariable Integer productionDetailId,@RequestBody ProductionDetail newProductionDetail){

        return productionDetailRepository.findById(productionDetailId).map(productionDetail -> {
            productionDetail.setId(newProductionDetail.getId());
            productionDetail.setProductionId(newProductionDetail.getProductionId());
            productionDetail.setProductionEvent(newProductionDetail.getProductionEvent());
            productionDetail.setEventdatetime(newProductionDetail.getEventdatetime());
            productionDetail.setMachineRegisterId(newProductionDetail.getMachineRegisterId());
            productionDetail.setTotaltime(newProductionDetail.getTotaltime());
            productionDetail.setLossId(newProductionDetail.getLossId());

            productionDetailRepository.save(productionDetail);
            return ResponseEntity.ok(productionDetail);
        }).orElseThrow(() -> new ResourceNotFoundException("ProductionDetail [productionDetailId="+productionDetailId+"] can't be found"));

    }
}
