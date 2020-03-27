package com.dbogheanu.parking.api.models.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.dbogheanu.parking.api.models.enums.EParkingSlotType;
import com.dbogheanu.parking.api.servicies.parkingfees.FixedAmountPlusTaxPerHourParkingFee;

public class BillTest {

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

    VisitHistory visitHistory = new VisitHistory();

    visitHistory.setId(1);
    visitHistory.setCar(car);
    visitHistory.setParking(parking);
    visitHistory.setEntryTime(LocalDateTime.now());
    visitHistory.setExitTime(LocalDateTime.now().plusDays(1));

    Bill bill = new Bill();

    bill.setId(1);
    bill.setParkingSlot(parkingSlot);
    bill.setVisitHistory(visitHistory);
    bill.setAmount(2.0);
    bill.setCreated(LocalDateTime.now());

    assertEquals(1, bill.getId());
    assertEquals(parkingSlot, bill.getParkingSlot());
    assertEquals(visitHistory, bill.getVisitHistory());
    assertEquals(2.0, bill.getAmount());
    assertNotEquals(LocalDateTime.now(), bill.getCreated());

  }
}
