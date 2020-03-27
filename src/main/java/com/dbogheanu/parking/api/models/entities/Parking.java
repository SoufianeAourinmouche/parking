package com.dbogheanu.parking.api.models.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "parking")
public class Parking extends AbstractEntity {

  @JoinColumn(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "parking_fee_id")
  private ParkingFee pricingPolicy;

  @Column(name = "created")
  @ColumnDefault("CURRENT_TIMESTAMP")
  private LocalDateTime created;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public ParkingFee getPricingPolicy() {
    return pricingPolicy;
  }

  public void setPricingPolicy(ParkingFee pricingPolicy) {
    this.pricingPolicy = pricingPolicy;
  }
}
