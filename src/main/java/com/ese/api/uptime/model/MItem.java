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

@Table(name = "m_item")
@Data
@Entity
public class MItem implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "part_number")
  private String partNumber;

  @Column(name = "part_name")
  private String partName;

  @Column(name = "partner_id")
  private Integer partnerId;

  @Column(name = "description1")
  private String description1;

  @Column(name = "description2")
  private String description2;

  @Column(name = "description3")
  private String description3;

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

  @Column(name = "type")
  private Integer type = 0;

  
}
