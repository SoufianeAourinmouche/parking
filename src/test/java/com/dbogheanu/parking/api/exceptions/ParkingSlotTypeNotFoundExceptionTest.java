package com.dbogheanu.parking.api.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParkingSlotTypeNotFoundExceptionTest {

  @Test
  void test() {
    ParkingSlotTypeNotFoundException exception = new ParkingSlotTypeNotFoundException("test message");

    assertEquals("test message", exception.getMessage());
  }
}
