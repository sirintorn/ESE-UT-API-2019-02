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

@Table(name = "operation_status")
@Entity
@Data
public class OperationStatus implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "operation_id")
  private Integer operationId;

  @Column(name = "status_id")
  private Integer statusId;

  @Column(name = "event_datetime")
  private java.util.Date eventDatetime = new Date();

  @Column(name = "is_active")
  private Boolean active = Boolean.FALSE;

  @Column(name = "factory_id")
  private Integer factoryId;

  @Column(name = "sixbigloss_id")
  private Integer sixbiglossId;

  
}
