package com.dbogheanu.parking.api.servicies;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dbogheanu.parking.api.models.entities.ParkingSlot;
import com.dbogheanu.parking.api.repositories.IParkingSlotRepository;
import com.dbogheanu.parking.api.servicies.interfaces.IService;

@Component
public class ParkingSlotService implements IService<ParkingSlot>{

  protected final IParkingSlotRepository parkingSlotRepository;
  protected final ParkingService parkingService;
  protected final CarService carService;
  protected final ParkingSlotTypeService parkingSlotTypeService;

  public ParkingSlotService(IParkingSlotRepository parkingSlotRepository,
                            ParkingService parkingService,
                            CarService carService,
                            ParkingSlotTypeService parkingSlotTypeService)
  {
      this.parkingSlotRepository = parkingSlotRepository;
      this.parkingService = parkingService;
      this.carService = carService;
      this.parkingSlotTypeService = parkingSlotTypeService;
  }

  @Override
  public ParkingSlot getEntity(int id)
  {
      return parkingSlotRepository.findById(id).orElse(null);
  }

  @Override
  public ParkingSlot saveEntity(ParkingSlot entity)
  {
      if (entity == null)
      {
          return null;
      }

      if (entity.getParkingSlotType() != null)
      {
          parkingSlotTypeService.saveEntity(entity.getParkingSlotType());
      }

      if (entity.getParking() != null)
      {
          parkingService.saveEntity(entity.getParking());
      }

      if (entity.getCar() != null)
      {
          carService.saveEntity(entity.getCar());
      }

      return parkingSlotRepository.save(entity);
  }

  @Override
  public boolean deleteEntity(ParkingSlot entity)
  {
      if (entity == null)
      {
          return false;
      }

      parkingSlotRepository.delete(entity);

      return parkingSlotRepository.findById(entity.getId()).orElse(null) == null;
  }

  public ParkingSlot findFreeSlotsForType(int parkingId, int parkingSlotTypeId)
  {
      List<ParkingSlot> parkingSlots = parkingSlotRepository.findFreeSlotsForType(parkingId, parkingSlotTypeId);

      if (parkingSlots != null && !parkingSlots.isEmpty())
      {
          return parkingSlots.get(0);
      }

      return null;
  }

  public Iterable<ParkingSlot> getAllEntities()
  {
      return parkingSlotRepository.findAll();
  }

  public ParkingSlot findParkingSlotByCarId(int carId)
  {
      return parkingSlotRepository.findParkingSlotByCarId(carId);
  }
}
