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

import com.dbogheanu.parking.api.models.entities.Car;
import com.dbogheanu.parking.api.repositories.RepositoryTestHelper;
import com.dbogheanu.parking.api.servicies.CarService;

public class CarServiceTest extends RepositoryTestHelper {

  @Autowired
  private CarService carService;

  @Test
  void testService() {
    Car car = new Car();

    car.setNumberPlate("DJ-322-NC");
    car.setCreationTime(LocalDateTime.now());

    Car savedCar = carService.saveEntity(car);

    assertNotNull(savedCar);
    assertEquals(1, ((ArrayList)carService.getAllCars()).size());

    savedCar.setNumberPlate("AB-831-EG");

    savedCar = carService.saveEntity(savedCar);

    assertNotNull(savedCar);
    assertEquals(1, ((ArrayList)carService.getAllCars()).size());

    car = new Car();

    car.setNumberPlate("EF-721-BE");
    car.setCreationTime(LocalDateTime.now());

    savedCar = carService.saveEntity(car);

    assertNotNull(savedCar);
    assertEquals(2, ((ArrayList)carService.getAllCars()).size());
    assertEquals("EF-721-BE", savedCar.getNumberPlate());

    assertNotNull(carService.getCarByNumberPlate("EF-721-BE"));
    assertNull(carService.getCarByNumberPlate(null));
    assertNull(carService.getCarByNumberPlate("AA-BB-AAA"));

    assertNull(carService.getEntity(-2));

    assertNotNull(carService.getEntity(savedCar.getId()));

    assertTrue(carService.deleteEntity(savedCar));
    assertEquals(1, ((ArrayList)carService.getAllCars()).size());

    assertFalse(carService.deleteEntity(null));
    assertNull(carService.saveEntity(null));
  }
}
