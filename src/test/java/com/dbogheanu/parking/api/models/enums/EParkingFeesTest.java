package com.dbogheanu.parking.api.models.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EParkingFeesTest {

  @Test
  void testLength() {
    assertEquals(2, EParkingFees.values().length);
  }

  @Test
  void testValues() {
    assertEquals(EParkingFees.TAX_PER_HOUR, EParkingFees.valueOf("PER_HOUR"));
    assertEquals(EParkingFees.FIXED_AMOUNT_PLUS_TAX_PER_HOUR, EParkingFees.valueOf("FIXED_AMOUNT_PLUS_PER_HOUR"));
  }
}
