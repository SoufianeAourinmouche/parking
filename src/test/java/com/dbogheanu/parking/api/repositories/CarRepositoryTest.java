package com.dbogheanu.parking.api.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.dbogheanu.parking.api.models.entities.Car;

public class CarRepositoryTest extends RepositoryTestHelper {

  @Test
  void repositoryTest() {
    assertEquals(0, ((ArrayList)carRepository.findAll()).size());

    Car car = new Car();

    car.setId(1);
    car.setNumberPlate("DJ-69-SHS");
    car.setCreationTime(LocalDateTime.now());

    Car savedCar = carRepository.save(car);

    assertNotNull(savedCar);
    assertEquals("DJ-69-SHS", savedCar.getNumberPlate());
  }

}
