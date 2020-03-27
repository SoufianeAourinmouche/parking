package com.dbogheanu.parking.api.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.dbogheanu.parking.api.models.entities.Parking;
import com.dbogheanu.parking.api.models.entities.ParkingFee;
import com.dbogheanu.parking.api.models.enums.EParkingFees;
import com.dbogheanu.parking.api.servicies.parkingfees.FixedAmountPlusTaxPerHourParkingFee;

public class ParkingRepositoryTest extends RepositoryTestHelper {

  @Test
  void repositoryTest() {
    assertEquals(0, ((ArrayList)parkingRepository.findAll()).size());

    ParkingFee parkingFee = new ParkingFee();

    parkingFee.setId(1);
    parkingFee.setType(EParkingFees.TAX_PER_HOUR);
    parkingFee.setFormula(new FixedAmountPlusTaxPerHourParkingFee(0.2f, 2.5f));
    parkingFee.setCreated(LocalDateTime.now());

    ParkingFee savedPricingPolicy = parkingFeeRepository.save(parkingFee);

    Parking parking = new Parking();

    parking.setId(1);
    parking.setName("Nice Massena");
    parking.setPricingPolicy(parkingFee);
    parking.setCreated(LocalDateTime.now());

    Parking savedParking = parkingRepository.save(parking);

    assertNotNull(savedParking);
    assertEquals("Nice Massena", savedParking.getName());
    assertEquals(savedPricingPolicy.getType(), savedParking.getPricingPolicy().getType());
  }
}
