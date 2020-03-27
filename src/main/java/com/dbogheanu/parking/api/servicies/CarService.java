package com.dbogheanu.parking.api.servicies;

import org.springframework.stereotype.Component;

import com.dbogheanu.parking.api.models.entities.Car;
import com.dbogheanu.parking.api.repositories.ICarRepository;
import com.dbogheanu.parking.api.servicies.interfaces.IService;

@Component
public class CarService implements IService<Car> {
  
  protected final ICarRepository carRepository;
  
  public CarService(ICarRepository carRepository)
  {
      this.carRepository = carRepository;
  }

  @Override
  public Car getEntity(int id) {
    return carRepository.findById(id).orElse(null);
  }

  @Override
  public Car saveEntity(Car entity) {
    if (entity == null) {
        return null;
    }

    return carRepository.save(entity);
  }

  @Override
  public boolean deleteEntity(Car entity) {
    if (entity == null) {
        return false;
    }

    carRepository.delete(entity);

    return carRepository.findById(entity.getId()).orElse(null) == null;

  }
  
  public Car getCarByNumberPlate(String numberPlate) {
      return carRepository.getCarByNumberPlate(numberPlate);
  }
  
  public Iterable<Car> getAllCars()
  {
      return carRepository.findAll();
  }
  
}
