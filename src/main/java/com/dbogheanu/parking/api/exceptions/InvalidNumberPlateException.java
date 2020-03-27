package com.dbogheanu.parking.api.exceptions;

public class InvalidNumberPlateException extends RuntimeException {

  private static final long serialVersionUID = -2245607422110389332L;

  public InvalidNumberPlateException(String message) {
    super(message);
  }
}
