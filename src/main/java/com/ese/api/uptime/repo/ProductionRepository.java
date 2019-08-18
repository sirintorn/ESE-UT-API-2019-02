package com.ese.api.uptime.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.ese.api.uptime.model.Production;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")

public interface ProductionRepository extends JpaRepository<Production, Integer>, JpaSpecificationExecutor<Production> {

    @Query(value = "FROM Production p WHERE p.machineRegisterId = :machineRegisterId and p.factoryId = :factoryId and p.statusId in(1,2)  " )
    List<Production> findByMachineRegisterId(Integer machineRegisterId,Integer factoryId) ;


}
