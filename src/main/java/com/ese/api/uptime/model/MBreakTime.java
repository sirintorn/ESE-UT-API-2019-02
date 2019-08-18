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

@Entity
@Table(name = "m_break_time")
@Data
public class MBreakTime implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "isuse")
  private Boolean isuse = Boolean.FALSE;

  @Column(name = "start_time")
  private String startTime;

  @Column(name = "finish_time")
  private String finishTime;

  @Column(name = "factory_id")
  private Integer factoryId;

  @Column(name = "create_datetime")
  private java.util.Date createDatetime = new Date();

  @Column(name = "create_by")
  private Integer createBy;

  @Column(name = "modify_datetime")
  private java.util.Date modifyDatetime = new Date();

  @Column(name = "is_active")
  private Boolean active = Boolean.FALSE;

  @Column(name = "modify_by")
  private Integer modifyBy;

  
}
