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

import com.dbogheanu.parking.api.models.entities.Parking;
import com.dbogheanu.parking.api.models.entities.ParkingFee;
import com.dbogheanu.parking.api.models.entities.ParkingSlotType;
import com.dbogheanu.parking.api.models.enums.EParkingFees;
import com.dbogheanu.parking.api.models.enums.EParkingSlotType;
import com.dbogheanu.parking.api.repositories.RepositoryTestHelper;
import com.dbogheanu.parking.api.servicies.ParkingService;
import com.dbogheanu.parking.api.servicies.parkingfees.FixedAmountPlusTaxPerHourParkingFee;

public class ParkingServiceTest extends RepositoryTestHelper {

  @Autowired
  private ParkingService parkingService;

  @Test
  void testService() {
    ParkingFee parkingFee = new ParkingFee();

    parkingFee.setId(1);
    parkingFee.setType(EParkingFees.FIXED_AMOUNT_PLUS_TAX_PER_HOUR);
    parkingFee.setFormula(new FixedAmountPlusTaxPerHourParkingFee(0.2f, 2.5f));
    parkingFee.setCreated(LocalDateTime.now());

    ParkingFee parkingFee2 = new ParkingFee();

    parkingFee2.setType(EParkingFees.FIXED_AMOUNT_PLUS_TAX_PER_HOUR);
    parkingFee2.setFormula(new FixedAmountPlusTaxPerHourParkingFee(0.2f, 2.5f));
    parkingFee2.setCreated(LocalDateTime.now());

    ParkingSlotType parkingSlotType = new ParkingSlotType();

    parkingSlotType.setName(EParkingSlotType.STANDARD);
    parkingSlotType.setCreated(LocalDateTime.now());

    Parking parking = new Parking();

    parking.setName("Nice Massena");
    parking.setPricingPolicy(parkingFee);
    parking.setCreated(LocalDateTime.now());

    Parking savedParking = parkingService.saveEntity(parking);

    assertNotNull(savedParking);
    assertEquals(1, ((ArrayList)parkingService.getAllEntities()).size());

    savedParking.setName("Greek Parking");

    savedParking = parkingService.saveEntity(savedParking);

    assertNotNull(savedParking);
    assertEquals(1, ((ArrayList)parkingService.getAllEntities()).size());
    assertEquals("Greek Parking", savedParking.getName());

    parking = new Parking();

    parking.setName("Nice Massena");
    parking.setPricingPolicy(parkingFee2);
    parking.setCreated(LocalDateTime.now());

    savedParking = parkingService.saveEntity(parking);

    assertNotNull(savedParking);
    assertEquals(2, ((ArrayList)parkingService.getAllEntities()).size());
    assertEquals(parkingFee2, savedParking.getPricingPolicy());

    assertNull(parkingService.getEntity(-2));

    assertNotNull(parkingService.getEntity(savedParking.getId()));

    assertTrue(parkingService.deleteEntity(savedParking));
    assertEquals(1, ((ArrayList)parkingService.getAllEntities()).size());

    assertFalse(parkingService.deleteEntity(null));
    assertNull(parkingService.saveEntity(null));
  }
}
