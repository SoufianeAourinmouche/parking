package com.dbogheanu.parking.api.models.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "visit_history")
public class VisitHistory extends AbstractEntity {

  @ManyToOne
  @JoinColumn(name = "parking_id")
  private Parking parking;

  @ManyToOne
  @JoinColumn(name = "car_id")
  private Car car;

  @Column(name = "entry_time")
  private LocalDateTime entryTime;

  @Column(name = "exit_time")
  private LocalDateTime exitTime;

  public Parking getParking() {
    return parking;
  }

  public void setParking(Parking parking) {
    this.parking = parking;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public LocalDateTime getEntryTime() {
    return entryTime;
  }

  public void setEntryTime(LocalDateTime entryTime) {
    this.entryTime = entryTime;
  }

  public LocalDateTime getExitTime() {
    return exitTime;
  }

  public void setExitTime(LocalDateTime exitTime) {
    this.exitTime = exitTime;
  }
}
