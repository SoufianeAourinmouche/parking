package com.dbogheanu.parking.api.models.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.dbogheanu.parking.api.models.enums.EParkingSlotType;
import com.dbogheanu.parking.api.servicies.parkingfees.FixedAmountPlusTaxPerHourParkingFee;

public class ParkingTest {

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

    assertEquals(1, parking.getId());
    assertEquals("Jean Medcine", parking.getName());
    assertEquals(parkingFee, parking.getPricingPolicy());
    assertNotEquals(LocalDateTime.now(), parking.getCreated());

  }
}
