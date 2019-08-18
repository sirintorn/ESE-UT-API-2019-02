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

@Table(name = "m_operation")
@Data
@Entity
public class MOperation implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "operation_code")
  private String operationCode;

  @Column(name = "operation_name")
  private String operationName;

  @Column(name = "create_datetime")
  private java.util.Date createDatetime = new Date();

  @Column(name = "create_by")
  private Integer createBy;

  @Column(name = "modify_datetime")
  private java.util.Date modifyDatetime = new Date();

  @Column(name = "modify_by")
  private Integer modifyBy;

  @Column(name = "is_active")
  private Boolean active = Boolean.FALSE;

  @Column(name = "factory_id")
  private Integer factoryId;

  @Column(name = "parent_id")
  private Integer parentId;

  @Column(name = "level")
  private Integer level = 0;

  @Column(name = "max_capacity")
  private Float maxCapacity;

  @Column(name = "takttime")
  private Float takttime;

  @Column(name = "last_status")
  private Integer lastStatus;

  
}
