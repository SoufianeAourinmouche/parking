package com.dbogheanu.parking.api.exceptions;

public class ParkingNotFoundException extends RuntimeException {
  
  private static final long serialVersionUID = -6808843823809345208L;

  public ParkingNotFoundException(String message) {
      super(message);
  }
}