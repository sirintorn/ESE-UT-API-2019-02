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

@Table(name = "m_app_user")
@Data
@Entity
public class MAppUser implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "is_active")
  private Boolean active = Boolean.FALSE;

  @Column(name = "factory_id")
  private Integer factoryId;

  @Column(name = "user_image")
  private String userImage;

  @Column(name = "ref_no")
  private String refNo;

  @Column(name = "create_by")
  private Integer createBy;

  @Column(name = "create_date")
  private java.util.Date createDatetime = new Date();

  @Column(name = "modify_by")
  private Integer modifyBy;

  @Column(name = "modify_date")
  private java.util.Date modifyDate = new Date() ;

  @Column(name = "email")
  private String email;

  @Column(name = "is_login")
  private Boolean login = Boolean.FALSE;

  @Column(name = "last_login")
  private java.util.Date  lastLogin = new Date() ;

  
}
