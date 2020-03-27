package com.dbogheanu.parking.api.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.dbogheanu.parking.api.models.entities.ParkingFee;
import com.dbogheanu.parking.api.models.enums.EParkingFees;
import com.dbogheanu.parking.api.servicies.parkingfees.FixedAmountPlusTaxPerHourParkingFee;

public class ParkingFeeRepositoryTest extends RepositoryTestHelper {

  @Test
  void testRepository() {
    assertEquals(0, ((ArrayList)parkingFeeRepository.findAll()).size());

    ParkingFee parkingFee = new ParkingFee();

    parkingFee.setId(1);
    parkingFee.setType(EParkingFees.TAX_PER_HOUR);
    parkingFee.setFormula(new FixedAmountPlusTaxPerHourParkingFee(0.2f, 2.5f));
    parkingFee.setCreated(LocalDateTime.now());

    ParkingFee savedPricingPolicy = parkingFeeRepository.save(parkingFee);

    assertNotNull(savedPricingPolicy);
    assertEquals(EParkingFees.TAX_PER_HOUR, savedPricingPolicy.getType());
  }
}
