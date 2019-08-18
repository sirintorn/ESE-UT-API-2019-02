package com.ese.api.uptime.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "production_detail")
public class ProductionDetail implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "production_id")
  private Integer productionId;

  @Column(name = "production_event")
  private String productionEvent;

  @Column(name = "eventdatetime")
  private java.util.Date eventdatetime = new Date();

  @Column(name = "machine_register_id")
  private Integer machineRegisterId;

  @Column(name = "totaltime")
  private Integer totaltime;

  @Column(name = "loss_id")
  private Integer lossId;

  @Column(name = "factory_id")
  private Integer factoryId;

  
}
