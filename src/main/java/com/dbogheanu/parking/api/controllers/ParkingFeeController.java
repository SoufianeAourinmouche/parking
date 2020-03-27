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

import com.dbogheanu.parking.api.models.entities.ParkingFee;
import com.dbogheanu.parking.api.servicies.ParkingFeeService;


@RestController
@RequestMapping(value = "parking/parkingFee")
public class ParkingFeeController {

  protected final ParkingFeeService parkingFeeService;

  public ParkingFeeController(ParkingFeeService parkingFeeService)
  {
      this.parkingFeeService = parkingFeeService;
  }
  
  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  public Iterable<ParkingFee> get()
  {
      return parkingFeeService.getAllEntities();
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public ParkingFee get(@PathVariable(name = "id") int id)
  {
      return parkingFeeService.getEntity(id);
  }

  @PostMapping()
  @ResponseStatus(value = HttpStatus.OK)
  public ParkingFee save(@RequestBody ParkingFee pricingPolicy)
  {
      return parkingFeeService.saveEntity(pricingPolicy);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public boolean delete(@PathVariable(name = "id") int id)
  {
      return parkingFeeService.deleteEntity(parkingFeeService.getEntity(id));
  }
  
}
