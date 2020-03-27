package com.dbogheanu.parking.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dbogheanu.parking.api.models.entities.ParkingSlotType;
import com.dbogheanu.parking.api.servicies.ParkingSlotTypeService;

@RestController
@RequestMapping(value = "parking/parkingSlotType")
public class ParkingSlotTypeController {

  protected final ParkingSlotTypeService parkingService;

  public ParkingSlotTypeController(ParkingSlotTypeService parkingService)
  {
      this.parkingService = parkingService;
  }

  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  public Iterable<ParkingSlotType> get()
  {
      return parkingService.getAllEntities();
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public ParkingSlotType get(@PathVariable(name = "id") int id)
  {
      return parkingService.getEntity(id);
  }

  @PostMapping()
  @ResponseStatus(value = HttpStatus.OK)
  public ParkingSlotType save(@RequestBody ParkingSlotType parkingSlotType)
  {
      return parkingService.saveEntity(parkingSlotType);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public boolean delete(@PathVariable(name = "id") int id)
  {
      return parkingService.deleteEntity(parkingService.getEntity(id));
  }
}
