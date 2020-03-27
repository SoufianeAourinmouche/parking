package com.dbogheanu.parking.api.models.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.dbogheanu.parking.api.models.enums.EParkingSlotType;
import com.dbogheanu.parking.api.servicies.parkingfees.FixedAmountPlusTaxPerHourParkingFee;

public class ParkingSlotTest {

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

    Parking parking = new Parking();

    parking.setId(1);
    parking.setName("Jean Medcine");
    parking.setPricingPolicy(parkingFee);
    parking.setCreated(LocalDateTime.now());

    ParkingSlot parkingSlot = new ParkingSlot();

    parkingSlot.setId(1);
    parkingSlot.setFree(true);
    parkingSlot.setParking(parking);
    parkingSlot.setParkingSlotType(parkingSlotType);
    parkingSlot.setCreated(LocalDateTime.now());

    assertEquals(1, parkingSlot.getId());
    assertTrue(parkingSlot.isFree());
    assertEquals(parkingSlotType, parkingSlot.getParkingSlotType());
    assertEquals(parking, parkingSlot.getParking());
    assertNotEquals(LocalDateTime.now(), parkingSlot.getCreated());
  }
}
