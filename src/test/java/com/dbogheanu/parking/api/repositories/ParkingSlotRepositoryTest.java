package com.dbogheanu.parking.api.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.dbogheanu.parking.api.models.entities.Car;
import com.dbogheanu.parking.api.models.entities.Parking;
import com.dbogheanu.parking.api.models.entities.ParkingFee;
import com.dbogheanu.parking.api.models.entities.ParkingSlot;
import com.dbogheanu.parking.api.models.entities.ParkingSlotType;
import com.dbogheanu.parking.api.models.enums.EParkingFees;
import com.dbogheanu.parking.api.models.enums.EParkingSlotType;
import com.dbogheanu.parking.api.servicies.parkingfees.FixedAmountPlusTaxPerHourParkingFee;

public class ParkingSlotRepositoryTest extends RepositoryTestHelper {

  @Test
  void testFindFreeSlotsForType() {
    assertEquals(0, ((ArrayList)parkingSlotRepository.findAll()).size());

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

    parkingSlotType = new ParkingSlotType();

    parkingSlotType.setId(2);
    parkingSlotType.setName(EParkingSlotType.HIGH_ELECTRICAL_POWER);
    parkingSlotType.setCreated(LocalDateTime.now());

    ParkingSlotType savedParkingSlotType2 = parkingSlotTypeRepository.save(parkingSlotType);

    parkingSlotType = new ParkingSlotType();

    parkingSlotType.setId(3);
    parkingSlotType.setName(EParkingSlotType.LOW_ELECTRICAL_POWER);
    parkingSlotType.setCreated(LocalDateTime.now());

    ParkingSlotType savedParkingSlotType3 = parkingSlotTypeRepository.save(parkingSlotType);

    Parking parking = new Parking();

    parking.setId(1);
    parking.setName("Nice Massena");
    parking.setPricingPolicy(savedPricingPolicy);
    parking.setCreated(LocalDateTime.now());

    Parking savedParking = parkingRepository.save(parking);

    Car car = new Car();

    car.setId(1);
    car.setNumberPlate("EG-721-NF");
    car.setCreationTime(LocalDateTime.now());

    Car savedCar = carRepository.save(car);

    ParkingSlot parkingSlot = new ParkingSlot();

    parkingSlot.setId(1);
    parkingSlot.setParking(savedParking);
    parkingSlot.setParkingSlotType(savedParkingSlotType);
    parkingSlot.setCar(car);
    parkingSlot.setFree(false);
    parkingSlot.setCreated(LocalDateTime.now());

    parkingSlotRepository.save(parkingSlot);

    parkingSlot = new ParkingSlot();

    parkingSlot.setId(2);
    parkingSlot.setParking(savedParking);
    parkingSlot.setParkingSlotType(savedParkingSlotType);
    parkingSlot.setFree(true);
    parkingSlot.setCreated(LocalDateTime.now());

    parkingSlotRepository.save(parkingSlot);

    parkingSlot = new ParkingSlot();

    parkingSlot.setId(3);
    parkingSlot.setParking(savedParking);
    parkingSlot.setParkingSlotType(savedParkingSlotType2);
    parkingSlot.setFree(true);
    parkingSlot.setCreated(LocalDateTime.now());

    parkingSlotRepository.save(parkingSlot);

    parkingSlot = new ParkingSlot();

    parkingSlot.setId(4);
    parkingSlot.setParking(savedParking);
    parkingSlot.setParkingSlotType(savedParkingSlotType3);
    parkingSlot.setFree(true);
    parkingSlot.setCreated(LocalDateTime.now());

    parkingSlotRepository.save(parkingSlot);

    List<ParkingSlot> results = parkingSlotRepository.findFreeSlotsForType(parking.getId(),
        savedParkingSlotType.getId());

    assertNotNull(results);
    assertEquals(1, results.size());
    assertEquals(2, results.get(0).getId());

    parkingSlot = new ParkingSlot();

    parkingSlot.setId(2);
    parkingSlot.setParking(savedParking);
    parkingSlot.setParkingSlotType(savedParkingSlotType);
    parkingSlot.setCar(car);
    parkingSlot.setFree(false);
    parkingSlot.setCreated(LocalDateTime.now());

    parkingSlotRepository.save(parkingSlot);

    assertEquals(4, ((ArrayList)parkingSlotRepository.findAll()).size());

    results = parkingSlotRepository.findFreeSlotsForType(parking.getId(), savedParkingSlotType.getId());

    assertNotNull(results);
    assertEquals(0, results.size());
  }
}
