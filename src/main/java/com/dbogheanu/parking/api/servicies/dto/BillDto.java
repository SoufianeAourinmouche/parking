package com.dbogheanu.parking.api.servicies.dto;

public class BillDto {

  private int parkingId;
  private int parkingSlotId;
  private int visitHistoryId;

  public BillDto() {
  }

  public BillDto(int parkingId, int parkingSlotId, int visitHistoryId) {
      this.parkingId = parkingId;
      this.parkingSlotId = parkingSlotId;
      this.visitHistoryId = visitHistoryId;
  }

  public int getParkingId() {
      return parkingId;
  }

  public void setParkingId(int parkingId) {
      this.parkingId = parkingId;
  }

  public int getParkingSlotId() {
      return parkingSlotId;
  }

  public void setParkingSlotId(int parkingSlotId) {
      this.parkingSlotId = parkingSlotId;
  }

  public int getVisitHistoryId() {
      return visitHistoryId;
  }

  public void setVisitHistoryId(int visitHistoryId) {
      this.visitHistoryId = visitHistoryId;
  }
}
