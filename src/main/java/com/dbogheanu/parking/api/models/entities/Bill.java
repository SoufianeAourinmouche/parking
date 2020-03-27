package com.dbogheanu.parking.api.models.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "bill")
public class Bill extends AbstractEntity {

  @ManyToOne
  @JoinColumn(name = "parking_slot_id")
  private ParkingSlot parkingSlot;

  @ManyToOne
  @JoinColumn(name = "visit_history_id")
  private VisitHistory visitHistory;

  @Column(name = "amount")
  private double amount;

  @Column(name = "created")
  @ColumnDefault("CURRENT_TIMESTAMP")
  private LocalDateTime created;

  public ParkingSlot getParkingSlot() {
    return parkingSlot;
  }

  public void setParkingSlot(ParkingSlot parkingSlot) {
    this.parkingSlot = parkingSlot;
  }

  public VisitHistory getVisitHistory() {
    return visitHistory;
  }

  public void setVisitHistory(VisitHistory visitHistory) {
    this.visitHistory = visitHistory;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

}
