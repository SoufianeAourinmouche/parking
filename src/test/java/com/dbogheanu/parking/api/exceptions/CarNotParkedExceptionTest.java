package com.dbogheanu.parking.api.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CarNotParkedExceptionTest {

  @Test
  void test() {
    CarNotParkedException exception = new CarNotParkedException("test message");

    assertEquals("test message", exception.getMessage());
  }
}
