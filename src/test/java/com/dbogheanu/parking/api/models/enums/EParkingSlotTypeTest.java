package com.dbogheanu.parking.api.models.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EParkingSlotTypeTest {

  @Test
  void testLength() {
    assertEquals(3, EParkingSlotType.values().length);
  }

  @Test
  void testValues() {
    assertEquals(EParkingSlotType.STANDARD, EParkingSlotType.valueOf("STANDARD"));
    assertEquals(EParkingSlotType.LOW_ELECTRICAL_POWER, EParkingSlotType.valueOf("LOW_ELECTRICAL_POWER"));
    assertEquals(EParkingSlotType.HIGH_ELECTRICAL_POWER, EParkingSlotType.valueOf("HIGH_ELECTRICAL_POWER"));
  }
}
