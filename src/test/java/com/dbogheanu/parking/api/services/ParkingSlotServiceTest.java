package com.dbogheanu.parking.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbogheanu.parking.api.models.entities.Car;
import com.dbogheanu.parking.api.models.entities.Parking;
import com.dbogheanu.parking.api.models.entities.ParkingFee;
import com.dbogheanu.parking.api.models.entities.ParkingSlot;
import com.dbogheanu.parking.api.models.entities.ParkingSlotType;
import com.dbogheanu.parking.api.models.enums.EParkingFees;
import com.dbogheanu.parking.api.models.enums.EParkingSlotType;
import com.dbogheanu.parking.api.repositories.RepositoryTestHelper;
import com.dbogheanu.parking.api.servicies.ParkingSlotService;
import com.dbogheanu.parking.api.servicies.parkingfees.FixedAmountPlusTaxPerHourParkingFee;

public class ParkingSlotServiceTest extends RepositoryTestHelper {

  @Autowired
  private ParkingSlotService parkingSlotService;

  @Test
  void testService()
  {
    ParkingFee pricingPolicy = new ParkingFee();

      pricingPolicy.setType(EParkingFees.TAX_PER_HOUR);
      pricingPolicy.setFormula(new FixedAmountPlusTaxPerHourParkingFee(0.5f, 2.5f));
      pricingPolicy.setCreated(LocalDateTime.now());

      ParkingSlotType parkingSlotType = new ParkingSlotType();

      parkingSlotType.setName(EParkingSlotType.STANDARD);
      parkingSlotType.setCreated(LocalDateTime.now());

      Parking parking = new Parking();

      parking.setName("Nice Massena");
      parking.setPricingPolicy(pricingPolicy);
      parking.setCreated(LocalDateTime.now());

      Car car = new Car();

      car.setNumberPlate("EG-721-NF");
      car.setCreationTime(LocalDateTime.now());

      Car car2 = new Car();

      car2.setNumberPlate("EG-322-NF");
      car2.setCreationTime(LocalDateTime.now());

      ParkingSlot parkingSlot = new ParkingSlot();

      parkingSlot.setFree(true);
      parkingSlot.setCar(car);
      parkingSlot.setParking(parking);
      parkingSlot.setParkingSlotType(parkingSlotType);
      parkingSlot.setCreated(LocalDateTime.now());

      ParkingSlot savedParkingSlot = parkingSlotService.saveEntity(parkingSlot);

      assertNotNull(savedParkingSlot);
      assertEquals(1, ((ArrayList) parkingSlotService.getAllEntities()).size());

      savedParkingSlot.setFree(false);

      savedParkingSlot = parkingSlotService.saveEntity(savedParkingSlot);

      assertNotNull(savedParkingSlot);
      assertEquals(1, ((ArrayList) parkingSlotService.getAllEntities()).size());
      assertFalse(savedParkingSlot.isFree());

      parkingSlot = new ParkingSlot();

      parkingSlot.setFree(false);
      parkingSlot.setCar(car2);
      parkingSlot.setParking(parking);
      parkingSlot.setParkingSlotType(parkingSlotType);
      parkingSlot.setCreated(LocalDateTime.now());

      savedParkingSlot = parkingSlotService.saveEntity(parkingSlot);

      assertNotNull(savedParkingSlot);
      assertEquals(2, ((ArrayList) parkingSlotService.getAllEntities()).size());
      assertEquals("EG-322-NF", savedParkingSlot.getCar().getNumberPlate());

      assertNull(parkingSlotService.getEntity(-2));

      assertNotNull(parkingSlotService.getEntity(savedParkingSlot.getId()));

      assertTrue(parkingSlotService.deleteEntity(savedParkingSlot));
      assertEquals(1, ((ArrayList) parkingSlotService.getAllEntities()).size());

      assertFalse(parkingSlotService.deleteEntity(null));
      assertNull(parkingSlotService.saveEntity(null));
  }
}
