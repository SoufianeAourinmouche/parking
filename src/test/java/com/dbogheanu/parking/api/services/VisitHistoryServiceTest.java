package com.dbogheanu.parking.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbogheanu.parking.api.exceptions.CarNotParkedException;
import com.dbogheanu.parking.api.exceptions.InvalidNumberPlateException;
import com.dbogheanu.parking.api.exceptions.ParkingNotFoundException;
import com.dbogheanu.parking.api.exceptions.ParkingSlotNotAvailableException;
import com.dbogheanu.parking.api.exceptions.ParkingSlotTypeNotFoundException;
import com.dbogheanu.parking.api.exceptions.VisitHistoryNotFoundException;
import com.dbogheanu.parking.api.models.entities.Car;
import com.dbogheanu.parking.api.models.entities.Parking;
import com.dbogheanu.parking.api.models.entities.ParkingFee;
import com.dbogheanu.parking.api.models.entities.ParkingSlot;
import com.dbogheanu.parking.api.models.entities.ParkingSlotType;
import com.dbogheanu.parking.api.models.entities.VisitHistory;
import com.dbogheanu.parking.api.models.enums.EParkingFees;
import com.dbogheanu.parking.api.models.enums.EParkingSlotType;
import com.dbogheanu.parking.api.repositories.RepositoryTestHelper;
import com.dbogheanu.parking.api.servicies.VisitHistoryService;
import com.dbogheanu.parking.api.servicies.parkingfees.FixedAmountPlusTaxPerHourParkingFee;

public class VisitHistoryServiceTest extends RepositoryTestHelper {

  @Autowired
  private VisitHistoryService visitHistoryService;

  @Test
  void testService() {
    ParkingFee parkingFee = new ParkingFee();

    parkingFee.setId(1);
    parkingFee.setType(EParkingFees.FIXED_AMOUNT_PLUS_TAX_PER_HOUR);
    parkingFee.setFormula(new FixedAmountPlusTaxPerHourParkingFee(0.2f, 2.5f));
    parkingFee.setCreated(LocalDateTime.now());


    ParkingSlotType parkingSlotType = new ParkingSlotType();

    parkingSlotType.setName(EParkingSlotType.STANDARD);
    parkingSlotType.setCreated(LocalDateTime.now());

    parkingSlotTypeRepository.save(parkingSlotType);

    Parking parking = new Parking();

    parking.setId(1);
    parking.setName("Jean Medcine");
    parking.setPricingPolicy(parkingFee);
    parking.setCreated(LocalDateTime.now());

    Car car = new Car();

    car.setId(1);
    car.setNumberPlate("DJ-69-SHS");
    car.setCreationTime(LocalDateTime.now());

    Car car2 = new Car();

    car2.setId(2);
    car2.setNumberPlate("DJ-88-AID");
    car2.setCreationTime(LocalDateTime.now());

    ParkingSlot parkingSlot = new ParkingSlot();

    parkingSlot.setId(1);
    parkingSlot.setFree(false);
    parkingSlot.setCar(car);
    parkingSlot.setParking(parking);
    parkingSlot.setParkingSlotType(parkingSlotType);
    parkingSlot.setCreated(LocalDateTime.now());

    VisitHistory visitHistory = new VisitHistory();

    visitHistory.setParking(parking);
    visitHistory.setCar(car);
    visitHistory.setEntryTime(LocalDateTime.now());

    VisitHistory savedVisitHistory = visitHistoryService.saveEntity(visitHistory);

    assertNotNull(savedVisitHistory);
    assertEquals(1, ((ArrayList)visitHistoryService.getAllEntities()).size());

    savedVisitHistory.setExitTime(LocalDateTime.now());

    savedVisitHistory = visitHistoryService.saveEntity(savedVisitHistory);

    assertNotNull(savedVisitHistory);
    assertEquals(1, ((ArrayList)visitHistoryService.getAllEntities()).size());
    assertNotNull(savedVisitHistory.getExitTime());

    visitHistory = new VisitHistory();

    visitHistory.setParking(parking);
    visitHistory.setCar(car2);
    visitHistory.setEntryTime(LocalDateTime.now());

    savedVisitHistory = visitHistoryService.saveEntity(visitHistory);

    assertNotNull(savedVisitHistory);
    assertEquals(2, ((ArrayList)visitHistoryService.getAllEntities()).size());
    assertNull(savedVisitHistory.getExitTime());

    assertNull(visitHistoryService.getEntity(-2));

    assertNotNull(visitHistoryService.getEntity(savedVisitHistory.getId()));

    assertTrue(visitHistoryService.deleteEntity(savedVisitHistory));
    assertEquals(1, ((ArrayList)visitHistoryService.getAllEntities()).size());

    assertFalse(visitHistoryService.deleteEntity(null));
    assertNull(visitHistoryService.saveEntity(null));

    assertThrows(ParkingNotFoundException.class,
        () -> visitHistoryService.checkIn(-2, car.getNumberPlate(), parkingSlotType.getName()));

    parkingSlotType.setName(EParkingSlotType.HIGH_ELECTRICAL_POWER);

    assertThrows(ParkingSlotTypeNotFoundException.class,
        () -> visitHistoryService.checkIn(parking.getId(), car.getNumberPlate(), parkingSlotType.getName()));

    parkingSlotType.setName(EParkingSlotType.STANDARD);

    assertThrows(ParkingSlotNotAvailableException.class,
        () -> visitHistoryService.checkIn(parking.getId(), car.getNumberPlate(), parkingSlotType.getName()));

    parkingSlot.setFree(true);
    parkingSlot.setCar(null);

    parkingSlotRepository.save(parkingSlot);

    VisitHistory checkedInVisit = visitHistoryService.checkIn(parking.getId(), "AAA", parkingSlotType.getName());

    assertNotNull(checkedInVisit);
    assertEquals("AAA", checkedInVisit.getCar().getNumberPlate());
    assertEquals(parking.getName(), checkedInVisit.getParking().getName());
    assertNotNull(checkedInVisit.getEntryTime());
    assertNull(checkedInVisit.getExitTime());

    assertThrows(ParkingNotFoundException.class, () -> visitHistoryService.checkOut(-2, "AAA"));

    assertThrows(InvalidNumberPlateException.class, () -> visitHistoryService.checkOut(parking.getId(), "BBB"));

    Car car3 = new Car();

    car3.setId(3);
    car3.setNumberPlate("BBB");
    car3.setCreationTime(LocalDateTime.now());

    carRepository.save(car3);

    parkingSlot.setCar(car3);

    parkingSlotRepository.save(parkingSlot);

    assertThrows(CarNotParkedException.class, () -> visitHistoryService.checkOut(parking.getId(), "DJ-69-SHS"));

    savedVisitHistory.setCar(car3);

    visitHistoryRepository.save(savedVisitHistory);

    parkingSlot.setCar(car);

    parkingSlotRepository.save(parkingSlot);

    assertThrows(VisitHistoryNotFoundException.class, () -> visitHistoryService.checkOut(parking.getId(), "DJ-69-SHS"));

  }
}
