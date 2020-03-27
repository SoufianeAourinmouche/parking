package com.dbogheanu.parking.api.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.dbogheanu.parking.api.models.entities.Car;
import com.dbogheanu.parking.api.models.entities.Parking;
import com.dbogheanu.parking.api.models.entities.ParkingFee;
import com.dbogheanu.parking.api.models.entities.ParkingSlot;
import com.dbogheanu.parking.api.models.entities.ParkingSlotType;
import com.dbogheanu.parking.api.models.entities.VisitHistory;
import com.dbogheanu.parking.api.models.enums.EParkingFees;
import com.dbogheanu.parking.api.models.enums.EParkingSlotType;
import com.dbogheanu.parking.api.servicies.parkingfees.FixedAmountPlusTaxPerHourParkingFee;

public class VisitHistoryRepositoryTest extends RepositoryTestHelper {

  @Test
  void testRepository() {
    assertEquals(0, ((ArrayList)visitHistoryRepository.findAll()).size());

    ParkingFee parkingFee = new ParkingFee();

    parkingFee.setId(1);
    parkingFee.setType(EParkingFees.TAX_PER_HOUR);
    parkingFee.setFormula(new FixedAmountPlusTaxPerHourParkingFee(0.2f, 2.5f));
    parkingFee.setCreated(LocalDateTime.now());

    ParkingFee savedPricingPolicy = parkingFeeRepository.save(parkingFee);

    ParkingSlotType parkingSlotType = new ParkingSlotType();

    parkingSlotType.setId(1);
    parkingSlotType.setName(EParkingSlotType.STANDARD);
    parkingSlotType.setCreated(LocalDateTime.now());

    ParkingSlotType savedParkingSlotType = parkingSlotTypeRepository.save(parkingSlotType);

    Parking parking = new Parking();

    parking.setId(1);
    parking.setName("Nice Massena");
    parking.setPricingPolicy(parkingFee);
    parking.setCreated(LocalDateTime.now());

    Parking savedParking = parkingRepository.save(parking);

    Car car = new Car();

    car.setId(1);
    car.setNumberPlate("EG-721-NF");
    car.setCreationTime(LocalDateTime.now());

    Car savedCar = carRepository.save(car);

    ParkingSlot parkingSlot = new ParkingSlot();

    parkingSlot.setId(1);
    parkingSlot.setFree(true);
    parkingSlot.setParking(parking);
    parkingSlot.setCar(car);
    parkingSlot.setParkingSlotType(savedParkingSlotType);
    parkingSlot.setCreated(LocalDateTime.now());

    ParkingSlot savedParkingSlot = parkingSlotRepository.save(parkingSlot);

    VisitHistory visitHistory = new VisitHistory();

    visitHistory.setId(1);
    visitHistory.setCar(car);
    visitHistory.setParking(parking);
    visitHistory.setEntryTime(LocalDateTime.now());
    visitHistory.setExitTime(LocalDateTime.now().plusDays(1));

    VisitHistory savedVisitLog = visitHistoryRepository.save(visitHistory);

    assertNotNull(visitHistory);
    assertEquals(savedCar.getNumberPlate(), savedVisitLog.getCar().getNumberPlate());
    assertEquals(savedParking.getName(), savedVisitLog.getParking().getName());
  }
}
