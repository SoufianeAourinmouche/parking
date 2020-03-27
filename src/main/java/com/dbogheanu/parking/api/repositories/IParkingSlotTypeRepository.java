package com.dbogheanu.parking.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.dbogheanu.parking.api.models.entities.ParkingSlotType;
import com.dbogheanu.parking.api.models.enums.EParkingSlotType;

public interface IParkingSlotTypeRepository extends CrudRepository<ParkingSlotType, Integer>{

  /**
   * Find parkingSlot type by Name
   *
   * @param name The name of the parkingSlotType
   * @return The parkingSlotType found
   */
  ParkingSlotType findParkingSlotTypeByName(EParkingSlotType name);
}
