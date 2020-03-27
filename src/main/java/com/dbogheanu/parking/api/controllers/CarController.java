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

import com.dbogheanu.parking.api.models.entities.Car;
import com.dbogheanu.parking.api.servicies.CarService;

@RestController
@RequestMapping(value = "parking/car")
public class CarController {
  
  protected final CarService carService;

  public CarController(CarService carService) {
      this.carService = carService;
  }
  
  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  public Iterable<Car> get() {
      return carService.getAllCars();
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public Car get(@PathVariable(name = "id") int id) {
      return carService.getEntity(id);
  }
  
  @PostMapping()
  @ResponseStatus(value = HttpStatus.OK)
  public Car save(@RequestBody Car car) {
      return carService.saveEntity(car);
  }
  
  @DeleteMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public boolean delete(@PathVariable(name = "id") int id) {
      return carService.deleteEntity(carService.getEntity(id));
  }
  
}
