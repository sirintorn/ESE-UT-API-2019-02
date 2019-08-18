package com.ese.api.uptime.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.ese.api.uptime.model.ProductionDetail;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")

public interface ProductionDetailRepository extends JpaRepository<ProductionDetail, Integer>, JpaSpecificationExecutor<ProductionDetail> {

}
