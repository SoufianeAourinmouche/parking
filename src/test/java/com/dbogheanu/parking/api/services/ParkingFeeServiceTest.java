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

import com.dbogheanu.parking.api.models.entities.ParkingFee;
import com.dbogheanu.parking.api.models.enums.EParkingFees;
import com.dbogheanu.parking.api.repositories.RepositoryTestHelper;
import com.dbogheanu.parking.api.servicies.ParkingFeeService;
import com.dbogheanu.parking.api.servicies.parkingfees.FixedAmountPlusTaxPerHourParkingFee;
import com.dbogheanu.parking.api.servicies.parkingfees.TaxPerHourParkingFee;

public class ParkingFeeServiceTest extends RepositoryTestHelper {

  @Autowired
  private ParkingFeeService parkingFeeService;

  @Test
  void testService()
  {
      ParkingFee parkingFee = new ParkingFee();

      parkingFee.setId(1);
      parkingFee.setType(EParkingFees.TAX_PER_HOUR);
      parkingFee.setFormula(new TaxPerHourParkingFee(0.5f));
      parkingFee.setCreated(LocalDateTime.now());

      ParkingFee savedParkingFee = parkingFeeService.saveEntity(parkingFee);

      assertNotNull(savedParkingFee);
      assertEquals(1, ((ArrayList) parkingFeeService.getAllEntities()).size());

      savedParkingFee.setType(EParkingFees.FIXED_AMOUNT_PLUS_TAX_PER_HOUR);
      savedParkingFee.setFormula(new FixedAmountPlusTaxPerHourParkingFee(0.5f, 2.5f));

      savedParkingFee = parkingFeeService.saveEntity(savedParkingFee);

      assertNotNull(savedParkingFee);
      assertEquals(1, ((ArrayList) parkingFeeService.getAllEntities()).size());
      assertEquals(EParkingFees.FIXED_AMOUNT_PLUS_TAX_PER_HOUR, savedParkingFee.getType());

      parkingFee = new ParkingFee();

      parkingFee.setType(EParkingFees.TAX_PER_HOUR);
      parkingFee.setFormula(new TaxPerHourParkingFee(0.5f));
      parkingFee.setCreated(LocalDateTime.now());

      savedParkingFee = parkingFeeService.saveEntity(parkingFee);

      assertNotNull(savedParkingFee);
      assertEquals(2, ((ArrayList) parkingFeeService.getAllEntities()).size());
      assertEquals(EParkingFees.TAX_PER_HOUR, savedParkingFee.getType());

  }
}
