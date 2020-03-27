package com.dbogheanu.parking.api.servicies;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.dbogheanu.parking.api.exceptions.CarNotParkedException;
import com.dbogheanu.parking.api.exceptions.InvalidNumberPlateException;
import com.dbogheanu.parking.api.exceptions.ParkingNotFoundException;
import com.dbogheanu.parking.api.exceptions.ParkingSlotNotAvailableException;
import com.dbogheanu.parking.api.exceptions.ParkingSlotTypeNotFoundException;
import com.dbogheanu.parking.api.exceptions.VisitHistoryNotFoundException;
import com.dbogheanu.parking.api.models.entities.Car;
import com.dbogheanu.parking.api.models.entities.Parking;
import com.dbogheanu.parking.api.models.entities.ParkingSlot;
import com.dbogheanu.parking.api.models.entities.ParkingSlotType;
import com.dbogheanu.parking.api.models.entities.VisitHistory;
import com.dbogheanu.parking.api.models.enums.EParkingSlotType;
import com.dbogheanu.parking.api.repositories.IVisitHistoryRepository;
import com.dbogheanu.parking.api.servicies.interfaces.IService;

@Component
public class VisitHistoryService  implements IService<VisitHistory> {

  protected final IVisitHistoryRepository visitHistoryRepository;
  protected final ParkingService parkingService;
  protected final CarService carService;
  protected final ParkingSlotService parkingSlotService;
  protected final ParkingSlotTypeService parkingSlotTypeService;

  public VisitHistoryService(IVisitHistoryRepository visitHistoryRepository,
                         ParkingService parkingService,
                         CarService carService,
                         ParkingSlotService parkingSlotService,
                         ParkingSlotTypeService parkingSlotTypeService)
  {
      this.visitHistoryRepository = visitHistoryRepository;
      this.parkingService = parkingService;
      this.carService = carService;
      this.parkingSlotService = parkingSlotService;
      this.parkingSlotTypeService = parkingSlotTypeService;
  }
  
  @Override
  public VisitHistory getEntity(int id)
  {
      return visitHistoryRepository.findById(id).orElse(null);
  }
  
  @Override
  public VisitHistory saveEntity(VisitHistory entity)
  {
      if (entity == null)
      {
          return null;
      }

      if (entity.getCar() != null)
      {
          carService.saveEntity(entity.getCar());
      }

      if (entity.getParking() != null)
      {
          parkingService.saveEntity(entity.getParking());
      }

      return visitHistoryRepository.save(entity);
  }
  
  @Override
  public boolean deleteEntity(VisitHistory entity)
  {
      if (entity == null)
      {
          return false;
      }

      visitHistoryRepository.delete(entity);

      return visitHistoryRepository.findById(entity.getId()).orElse(null) == null;
  }

  public Iterable<VisitHistory> getAllEntities()
  {
      return visitHistoryRepository.findAll();
  }
  
  /**
   * Check in the car in one parking for a specific parkingSlotType
   *
   * @param parkingId         The parking id
   * @param registrationPlate The car's registration plate
   * @param parkingSlotType   The parking Slot Type for we need to find place for.
   * @return The VisitHistory created
   */
  public VisitHistory checkIn(int parkingId, String registrationPlate, EParkingSlotType parkingSlotType)
  {
      Parking parking = parkingService.getEntity(parkingId);

      if (parking == null)
      {
          throw new ParkingNotFoundException("Parking with id " + parkingId + " could not be found");
      }

      Car car = carService.getCarByNumberPlate(registrationPlate);

      if (car == null)
      {
          car = new Car();

          car.setNumberPlate(registrationPlate);
          car.setCreationTime(LocalDateTime.now());

          carService.saveEntity(car);
      }

      ParkingSlotType parkingSlotTypeFromDb = parkingSlotTypeService.findParkingSlotTypeByName(parkingSlotType);

      if (parkingSlotTypeFromDb == null)
      {
          throw new ParkingSlotTypeNotFoundException("ParkingSlotType with name" + parkingSlotType.name() + " was not found");
      }

      ParkingSlot parkingSlot = parkingSlotService.findFreeSlotsForType(parkingId, parkingSlotTypeFromDb.getId());

      if (parkingSlot == null)
      {
          throw new ParkingSlotNotAvailableException(
                  "No available position for " + parkingSlotType.name() + " type cars");
      }

      parkingSlot.setCar(car);
      parkingSlot.setFree(false);

      parkingSlotService.saveEntity(parkingSlot);

      VisitHistory visitHistory = new VisitHistory();

      visitHistory.setParking(parking);
      visitHistory.setCar(car);
      visitHistory.setEntryTime(LocalDateTime.now());

      return visitHistoryRepository.save(visitHistory);
  }
  
  /**
   * Check out the car from its parkingSlot
   *
   * @param parkingId The parking id
   * @param registrationPlate The car's registration plate
   * @return The VisitHistory with exit time
   */
  public VisitHistory checkOut(int parkingId, String registrationPlate)
  {
      Parking parking = parkingService.getEntity(parkingId);

      if (parking == null)
      {
          throw new ParkingNotFoundException("Parking with id " + parkingId + " could not be found");
      }

      Car car = carService.getCarByNumberPlate(registrationPlate);

      if (car == null)
      {
          throw new InvalidNumberPlateException(
                  "No car with number plate " + registrationPlate + " was found");
      }

      ParkingSlot parkingSlot = parkingSlotService.findParkingSlotByCarId(car.getId());

      if (parkingSlot == null)
      {
          throw new CarNotParkedException("The car was not parked");
      }

      parkingSlot.setFree(true);
      parkingSlot.setCar(null);

      VisitHistory visitHistory = visitHistoryRepository.findLatestVisitByCarId(parkingId, car.getId());

      if (visitHistory == null)
      {
          throw new VisitHistoryNotFoundException("VisitHistoryNotFound");
      }

      visitHistory.setExitTime(LocalDateTime.now());

      return visitHistoryRepository.save(visitHistory);
  }

}
