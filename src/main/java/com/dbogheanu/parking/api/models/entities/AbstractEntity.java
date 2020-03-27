package com.dbogheanu.parking.api.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.dbogheanu.parking.api.models.interfaces.Identifiable;

@MappedSuperclass
public abstract class AbstractEntity implements Identifiable, Serializable {
  @Id
  @Column(name = "id")
  @GenericGenerator(name = "assigned-identity", strategy = "com.dbogheanu.parking.api.utils.AssignedIdentityGenerator")
  @GeneratedValue(generator = "assigned-identity", strategy = GenerationType.IDENTITY)
  protected Integer id;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
