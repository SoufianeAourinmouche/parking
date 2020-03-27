package com.dbogheanu.parking.api.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParkingNotFoundExceptionTest {

  @Test
  void test() {
    ParkingNotFoundException exception = new ParkingNotFoundException("test message");

    assertEquals("test message", exception.getMessage());
  }
}
