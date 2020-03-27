package com.dbogheanu.parking.api.exceptions;

public class ParkingSlotNotAvailableException extends RuntimeException {

  private static final long serialVersionUID = 3196508720886670142L;

  public ParkingSlotNotAvailableException(String message) {
    super(message);
  }
}
