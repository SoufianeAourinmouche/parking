package com.dbogheanu.parking.api.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParkingSlotNotAvailableExceptionTest {

  @Test
  void test() {
    ParkingSlotNotAvailableException exception = new ParkingSlotNotAvailableException("test message");

    assertEquals("test message", exception.getMessage());
  }
}
