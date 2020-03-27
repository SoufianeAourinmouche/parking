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

import com.dbogheanu.parking.api.models.entities.Bill;
import com.dbogheanu.parking.api.models.entities.Car;
import com.dbogheanu.parking.api.models.entities.Parking;
import com.dbogheanu.parking.api.models.entities.ParkingFee;
import com.dbogheanu.parking.api.models.entities.ParkingSlot;
import com.dbogheanu.parking.api.models.entities.ParkingSlotType;
import com.dbogheanu.parking.api.models.entities.VisitHistory;
import com.dbogheanu.parking.api.models.enums.EParkingFees;
import com.dbogheanu.parking.api.models.enums.EParkingSlotType;
import com.dbogheanu.parking.api.repositories.RepositoryTestHelper;
import com.dbogheanu.parking.api.servicies.BillService;
import com.dbogheanu.parking.api.servicies.parkingfees.FixedAmountPlusTaxPerHourParkingFee;

public class BillServiceTest extends RepositoryTestHelper {
  
  @Autowired
  private BillService billService;

  @Test
  void testService()
  {
      ParkingFee parkingFee = new ParkingFee();

      parkingFee.setId(1);
      parkingFee.setType(EParkingFees.FIXED_AMOUNT_PLUS_TAX_PER_HOUR);
      parkingFee.setFormula(new FixedAmountPlusTaxPerHourParkingFee(0.5f, 2.5f));
      parkingFee.setCreated(LocalDateTime.now());

      ParkingSlotType parkingSlotType = new ParkingSlotType();

      parkingSlotType.setName(EParkingSlotType.STANDARD);
      parkingSlotType.setCreated(LocalDateTime.now());

      Parking parking = new Parking();

      parking.setName("Jean Medcine");
      parking.setPricingPolicy(parkingFee);
      parking.setCreated(LocalDateTime.now());

      Car car = new Car();

      car.setNumberPlate("EG-721-NF");
      car.setCreationTime(LocalDateTime.now());

      ParkingSlot parkingSlot = new ParkingSlot();

      parkingSlot.setFree(true);
      parkingSlot.setCar(car);
      parkingSlot.setParking(parking);
      parkingSlot.setParkingSlotType(parkingSlotType);
      parkingSlot.setCreated(LocalDateTime.now());

      VisitHistory visitHistory = new VisitHistory();

      visitHistory.setCar(car);
      visitHistory.setParking(parking);
      visitHistory.setEntryTime(LocalDateTime.now());
      visitHistory.setExitTime(LocalDateTime.now().plusDays(1));

      Bill bill = new Bill();

      bill.setParkingSlot(parkingSlot);
      bill.setVisitHistory(visitHistory);
      bill.setAmount(2.0);
      bill.setCreated(LocalDateTime.now());

      Bill savedBill = billService.saveEntity(bill);

      assertNotNull(savedBill);
      assertEquals(1, ((ArrayList) billService.getAllEntities()).size());

      savedBill.setAmount(5.0);

      savedBill = billService.saveEntity(savedBill);

      assertNotNull(savedBill);
      assertEquals(1, ((ArrayList) billService.getAllEntities()).size());
      assertEquals(5.0, savedBill.getAmount());

      bill = new Bill();

      bill.setParkingSlot(parkingSlot);
      bill.setVisitHistory(visitHistory);
      bill.setAmount(2.0);
      bill.setCreated(LocalDateTime.now());

      savedBill = billService.saveEntity(bill);

      assertNotNull(savedBill);
      assertEquals(2, ((ArrayList) billService.getAllEntities()).size());
      assertEquals(2.0, savedBill.getAmount());

      assertNull(billService.getEntity(-2));

      assertNotNull(billService.getEntity(savedBill.getId()));

      assertTrue(billService.deleteEntity(savedBill));
      assertEquals(1, ((ArrayList) billService.getAllEntities()).size());

      assertFalse(billService.deleteEntity(null));
      assertNull(billService.saveEntity(null));

      Bill realBill = billService.createPaymentFromVisitLogAndParkingSlot(parking, parkingSlot, visitHistory);

      assertNotNull(realBill);
      assertEquals(14.5, realBill.getAmount());
  }
  
}
