package com.dbogheanu.parking.api.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InvalidNumberPlateExceptionTest {

  @Test
  void test() {
    InvalidNumberPlateException exception = new InvalidNumberPlateException("test message");

    assertEquals("test message", exception.getMessage());
  }
}
