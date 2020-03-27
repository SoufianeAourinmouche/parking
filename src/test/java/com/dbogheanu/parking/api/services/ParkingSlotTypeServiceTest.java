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

import com.dbogheanu.parking.api.models.entities.ParkingSlotType;
import com.dbogheanu.parking.api.models.enums.EParkingSlotType;
import com.dbogheanu.parking.api.repositories.RepositoryTestHelper;
import com.dbogheanu.parking.api.servicies.ParkingSlotTypeService;

public class ParkingSlotTypeServiceTest extends RepositoryTestHelper {

  @Autowired
  private ParkingSlotTypeService parkingSlotTypeService;
  
  @Test
  void testService()
  {
    ParkingSlotType parkingSlotType = new ParkingSlotType();

      parkingSlotType.setName(EParkingSlotType.STANDARD);
      parkingSlotType.setCreated(LocalDateTime.now());

      ParkingSlotType savedParkingSlotType = parkingSlotTypeService.saveEntity(parkingSlotType);

      assertNotNull(savedParkingSlotType);
      assertEquals(1, ((ArrayList) parkingSlotTypeRepository.findAll()).size());

      savedParkingSlotType.setName(EParkingSlotType.HIGH_ELECTRICAL_POWER);

      savedParkingSlotType = parkingSlotTypeService.saveEntity(savedParkingSlotType);

      assertNotNull(savedParkingSlotType);
      assertEquals(1, ((ArrayList) parkingSlotTypeService.getAllEntities()).size());
      assertEquals(EParkingSlotType.HIGH_ELECTRICAL_POWER, savedParkingSlotType.getName());

      parkingSlotType = new ParkingSlotType();

      parkingSlotType.setName(EParkingSlotType.LOW_ELECTRICAL_POWER);
      parkingSlotType.setCreated(LocalDateTime.now());

      savedParkingSlotType = parkingSlotTypeService.saveEntity(parkingSlotType);

      assertNotNull(savedParkingSlotType);
      assertEquals(2, ((ArrayList) parkingSlotTypeService.getAllEntities()).size());
      assertEquals(EParkingSlotType.LOW_ELECTRICAL_POWER, savedParkingSlotType.getName());

      assertNull(parkingSlotTypeService.getEntity(-2));

      assertNotNull(parkingSlotTypeService.getEntity(savedParkingSlotType.getId()));

      assertTrue(parkingSlotTypeService.deleteEntity(savedParkingSlotType));
      assertEquals(1, ((ArrayList) parkingSlotTypeService.getAllEntities()).size());

      assertFalse(parkingSlotTypeService.deleteEntity(null));
      assertNull(parkingSlotTypeService.saveEntity(null));

      assertNull(parkingSlotTypeService.findParkingSlotTypeByName(EParkingSlotType.LOW_ELECTRICAL_POWER));

      assertNotNull(parkingSlotTypeService.findParkingSlotTypeByName(EParkingSlotType.HIGH_ELECTRICAL_POWER));
  }
  
}
