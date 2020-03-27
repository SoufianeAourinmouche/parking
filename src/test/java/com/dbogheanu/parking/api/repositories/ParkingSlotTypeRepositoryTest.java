package com.dbogheanu.parking.api.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.dbogheanu.parking.api.models.entities.ParkingSlotType;
import com.dbogheanu.parking.api.models.enums.EParkingSlotType;

public class ParkingSlotTypeRepositoryTest extends RepositoryTestHelper {

  @Test
  void repositoryTest() {
    assertEquals(0, ((ArrayList)parkingSlotTypeRepository.findAll()).size());

    ParkingSlotType parkingSlotType = new ParkingSlotType();

    parkingSlotType.setId(1);
    parkingSlotType.setName(EParkingSlotType.STANDARD);
    parkingSlotType.setCreated(LocalDateTime.now());

    ParkingSlotType savedParkingSlotType = parkingSlotTypeRepository.save(parkingSlotType);

    assertNotNull(savedParkingSlotType);
    assertEquals(EParkingSlotType.STANDARD, savedParkingSlotType.getName());
  }
}
