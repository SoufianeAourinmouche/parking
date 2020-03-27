package com.dbogheanu.parking.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.dbogheanu.parking.api.models.entities.Car;

public interface ICarRepository extends CrudRepository<Car, Integer> {
  
  /**
   * Based on the unique number plate find the car
   *
   * @param numberPlate The number plate that we need to find
   * @return The car with the number plate
   */
  Car getCarByNumberPlate(String numberPlate);

}
