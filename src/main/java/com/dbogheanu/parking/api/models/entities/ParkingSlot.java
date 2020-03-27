package com.dbogheanu.parking.api.models.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "parking_slot")
public class ParkingSlot extends AbstractEntity {

  @ManyToOne
  @JoinColumn(name = "parking_id")
  private Parking parking;

  @ManyToOne
  @JoinColumn(name = "parking_slot_type_id")
  private ParkingSlotType parkingSlotType;

  @ManyToOne
  @JoinColumn(name = "car_id")
  private Car car;

  @Column(name = "is_free")
  private boolean isFree;

  @Column(name = "created")
  @ColumnDefault("CURRENT_TIMESTAMP")
  private LocalDateTime created;

  public Parking getParking() {
    return parking;
  }

  public void setParking(Parking parking) {
    this.parking = parking;
  }

  public ParkingSlotType getParkingSlotType() {
    return parkingSlotType;
  }

  public void setParkingSlotType(ParkingSlotType parkingSlotType) {
    this.parkingSlotType = parkingSlotType;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public boolean isFree() {
    return isFree;
  }

  public void setFree(boolean free) {
    isFree = free;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }
}
