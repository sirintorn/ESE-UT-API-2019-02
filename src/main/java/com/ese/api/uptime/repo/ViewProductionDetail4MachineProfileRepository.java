package com.ese.api.uptime.repo;

import com.ese.api.uptime.model.ViewProductionDetail4MachineProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")

public interface ViewProductionDetail4MachineProfileRepository extends JpaRepository<ViewProductionDetail4MachineProfile, Integer>, JpaSpecificationExecutor<ViewProductionDetail4MachineProfile> {

}
