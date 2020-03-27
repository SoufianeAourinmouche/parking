package com.dbogheanu.parking.api.servicies.dto;

import java.io.Serializable;

public class VisitParkingDto implements Serializable {
  
  private static final long serialVersionUID = 6972396299513074182L;
  
  private int parkingId;
  private String registrationPlate;
  private String parkingSlotTypeName;

  public VisitParkingDto() {
  }

  public VisitParkingDto(int parkingId, String registrationPlate, String parkingSlotTypeName) {
      this.parkingId = parkingId;
      this.registrationPlate = registrationPlate;
      this.parkingSlotTypeName = parkingSlotTypeName;
  }

  public int getParkingId() {
      return parkingId;
  }

  public void setParkingId(int parkingId) {
      this.parkingId = parkingId;
  }

  public String getRegistrationPlate() {
      return registrationPlate;
  }

  public void setRegistrationPlate(String registrationPlate) {
      this.registrationPlate = registrationPlate;
  }

  public String getParkingSlotTypeName() {
      return parkingSlotTypeName;
  }

  public void setParkingSlotType(String parkingSlotTypeName) {
      this.parkingSlotTypeName = parkingSlotTypeName;
  }
}
