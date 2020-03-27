package com.dbogheanu.parking.api.models.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.dbogheanu.parking.api.models.enums.EParkingSlotType;

@Entity
@Table(name = "parking_slot_type")
public class ParkingSlotType extends AbstractEntity {

  @Column(name = "name")
  @Enumerated(EnumType.STRING)
  private EParkingSlotType name;

  @Column(name = "created")
  @ColumnDefault("CURRENT_TIMESTAMP")
  private LocalDateTime created;

  public EParkingSlotType getName() {
    return name;
  }

  public void setName(EParkingSlotType name) {
    this.name = name;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }
}
