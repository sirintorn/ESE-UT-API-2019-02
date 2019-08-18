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

@Table(name = "m_factory")
@Data
@Entity
public class MFactory implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;

  @Column(name = "phone")
  private String phone;

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

  @Column(name = "admin_id")
  private Integer adminId;

  
}
