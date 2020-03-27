package com.dbogheanu.parking.api.exceptions;

public class CarNotParkedException extends RuntimeException {
  
  private static final long serialVersionUID = -5918982469994342401L;

  public CarNotParkedException(String message) {
    super(message);
  }

}
