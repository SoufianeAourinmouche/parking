package com.dbogheanu.parking.api.models.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.dbogheanu.parking.api.models.enums.EParkingSlotType;
import com.dbogheanu.parking.api.servicies.parkingfees.FixedAmountPlusTaxPerHourParkingFee;

public class ParkingSlotTypeTest {

  @Test
  void test() {
    
    ParkingFee parkingFee = new ParkingFee();

    parkingFee.setId(1);
    parkingFee.setFormula(new FixedAmountPlusTaxPerHourParkingFee(0.2f, 2.5f));
    parkingFee.setCreated(LocalDateTime.now());

    ParkingSlotType parkingSlotType = new ParkingSlotType();

    parkingSlotType.setId(1);
    parkingSlotType.setName(EParkingSlotType.STANDARD);
    parkingSlotType.setCreated(LocalDateTime.now());

    assertEquals(1, parkingSlotType.getId());
    assertEquals(EParkingSlotType.STANDARD, parkingSlotType.getName());
    assertNotEquals(LocalDateTime.now(), parkingSlotType.getCreated());

    ParkingSlotType parkingSlotType2 = new ParkingSlotType();

    parkingSlotType2.setId(2);
    parkingSlotType2.setName(EParkingSlotType.HIGH_ELECTRICAL_POWER);
    parkingSlotType2.setCreated(LocalDateTime.now());

    assertEquals(2, parkingSlotType2.getId());
    assertEquals(EParkingSlotType.HIGH_ELECTRICAL_POWER, parkingSlotType2.getName());
  }
}
