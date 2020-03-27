package com.dbogheanu.parking.api.servicies;

import org.springframework.stereotype.Component;

import com.dbogheanu.parking.api.models.entities.ParkingFee;
import com.dbogheanu.parking.api.repositories.IParkingFeeRepository;
import com.dbogheanu.parking.api.servicies.interfaces.IService;

@Component
public class ParkingFeeService implements IService<ParkingFee>{

  protected final IParkingFeeRepository parkingFeeRepository;

  public ParkingFeeService(IParkingFeeRepository parkingFeeRepository)
  {
      this.parkingFeeRepository = parkingFeeRepository;
  }
  
  @Override
  public ParkingFee getEntity(int id)
  {
      return parkingFeeRepository.findById(id).orElse(null);
  }

  @Override
  public ParkingFee saveEntity(ParkingFee entity)
  {
      if (entity == null)
      {
          return null;
      }

      return parkingFeeRepository.save(entity);
  }

  @Override
  public boolean deleteEntity(ParkingFee entity)
  {
      if (entity == null)
      {
          return false;
      }

      parkingFeeRepository.delete(entity);

      return parkingFeeRepository.findById(entity.getId()).orElse(null) == null;
  }

  public Iterable<ParkingFee> getAllEntities()
  {
      return parkingFeeRepository.findAll();
  }
  
}
