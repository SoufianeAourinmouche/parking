package com.dbogheanu.parking.api.models.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.dbogheanu.parking.api.models.enums.EParkingFees;
import com.dbogheanu.parking.api.servicies.interfaces.IParkingFees;
import com.dbogheanu.parking.api.utils.BlobToClassConverter;

@Entity
@Table(name = "parking_fee")
public class ParkingFee extends AbstractEntity {

  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private EParkingFees type;

  @Column(name = "formula")
  @Convert(converter = BlobToClassConverter.class)
  private IParkingFees formula;

  @Column(name = "created")
  @ColumnDefault("CURRENT_TIMESTAMP")
  private LocalDateTime created;

  public EParkingFees getType() {
    return type;
  }

  public void setType(EParkingFees type) {
    this.type = type;
  }

  public IParkingFees getFormula() {
    return formula;
  }

  public void setFormula(IParkingFees formula) {
    this.formula = formula;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

}
