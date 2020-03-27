package com.dbogheanu.parking.api.models.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.dbogheanu.parking.api.models.entities.Car;
import com.dbogheanu.parking.api.models.entities.Parking;
import com.dbogheanu.parking.api.models.entities.ParkingFee;
import com.dbogheanu.parking.api.models.entities.ParkingSlot;
import com.dbogheanu.parking.api.models.entities.ParkingSlotType;
import com.dbogheanu.parking.api.models.enums.EParkingSlotType;
import com.dbogheanu.parking.api.servicies.parkingfees.FixedAmountPlusTaxPerHourParkingFee;

public class CarTest {

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

    Car car = new Car();

    car.setId(1);
    car.setNumberPlate("DJ-69-SHS");
    car.setCreationTime(LocalDateTime.now());

    ParkingSlot parkingSlot = new ParkingSlot();

    parkingSlot.setId(1);
    parkingSlot.setFree(true);
    parkingSlot.setParkingSlotType(parkingSlotType);
    parkingSlot.setCar(car);
    parkingSlot.setCreated(LocalDateTime.now());

    assertEquals(1, car.getId());
    assertEquals("DJ-69-SHS", car.getNumberPlate());
    assertNotEquals(LocalDateTime.now(), car.getCreationTime());

  }
}
