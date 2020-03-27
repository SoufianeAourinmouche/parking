package com.dbogheanu.parking.api.models.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car extends AbstractEntity {

  @Column(name = "number_plate", unique = true)
  private String numberPlate;

  @Column(name = "creation_time")
  private LocalDateTime creationTime = LocalDateTime.now();

  public String getNumberPlate() {
    return numberPlate;
  }

  public void setNumberPlate(String numberPlate) {
    this.numberPlate = numberPlate;
  }

  public LocalDateTime getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(LocalDateTime creationTime) {
    this.creationTime = creationTime;
  }

}
