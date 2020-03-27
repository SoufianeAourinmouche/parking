package com.dbogheanu.parking.api.exceptions;

public class ParkingSlotTypeNotFoundException extends RuntimeException {
  
  private static final long serialVersionUID = -7633968590188759202L;

  public ParkingSlotTypeNotFoundException(String message) {
      super(message);
  }
}
