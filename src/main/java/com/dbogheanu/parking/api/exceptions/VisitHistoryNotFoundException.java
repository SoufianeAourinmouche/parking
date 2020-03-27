package com.dbogheanu.parking.api.exceptions;

public class VisitHistoryNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 2822286256221773068L;

  public VisitHistoryNotFoundException(String message) {
    super(message);
  }
}
