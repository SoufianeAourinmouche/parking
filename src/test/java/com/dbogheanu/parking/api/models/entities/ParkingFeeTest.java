package com.dbogheanu.parking.api.models.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.dbogheanu.parking.api.models.enums.EParkingFees;
import com.dbogheanu.parking.api.servicies.parkingfees.FixedAmountPlusTaxPerHourParkingFee;

public class ParkingFeeTest {

  @Test
  void test() {
      ParkingFee parkingFee = new ParkingFee();

      parkingFee.setId(1);
      parkingFee.setType(EParkingFees.TAX_PER_HOUR);
      parkingFee.setFormula(new FixedAmountPlusTaxPerHourParkingFee(0.2f, 2.5f));
      parkingFee.setCreated(LocalDateTime.now());

      assertEquals(1, parkingFee.getId());
      assertEquals(EParkingFees.TAX_PER_HOUR, parkingFee.getType());
      assertEquals(new FixedAmountPlusTaxPerHourParkingFee(0.2f, 2.5f), parkingFee.getFormula());
      assertNotEquals(LocalDateTime.now(), parkingFee.getCreated());

      ParkingFee parkingFee2 = new ParkingFee();

      parkingFee2.setId(2);
      parkingFee2.setType(EParkingFees.FIXED_AMOUNT_PLUS_TAX_PER_HOUR);
      parkingFee2.setFormula(new FixedAmountPlusTaxPerHourParkingFee(0.8f, 2.6f));
      parkingFee2.setCreated(LocalDateTime.now());

      assertEquals(2, parkingFee.getId());
      assertEquals(EParkingFees.FIXED_AMOUNT_PLUS_TAX_PER_HOUR, parkingFee.getType());
      assertEquals(new FixedAmountPlusTaxPerHourParkingFee(0.8f, 2.6f), parkingFee.getFormula());
      assertNotEquals(LocalDateTime.now(), parkingFee.getCreated());
  }
}
