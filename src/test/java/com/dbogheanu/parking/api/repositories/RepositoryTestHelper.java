package com.dbogheanu.parking.api.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dbogheanu.parking.api.ParkingTollApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ParkingTollApplication.class)
@ActiveProfiles({"test"})
public class RepositoryTestHelper {

  @Autowired
  protected IBillRepository billRepository;
  @Autowired
  protected ICarRepository carRepository;
  @Autowired
  protected IVisitHistoryRepository visitHistoryRepository;
  @Autowired
  protected IParkingRepository parkingRepository;
  @Autowired
  protected IParkingSlotRepository parkingSlotRepository;
  @Autowired
  protected IParkingSlotTypeRepository parkingSlotTypeRepository;
  @Autowired
  protected IParkingFeeRepository parkingFeeRepository;

  @BeforeEach
  void setUp() {
    billRepository.deleteAll();
    visitHistoryRepository.deleteAll();
    parkingRepository.deleteAll();
    parkingSlotRepository.deleteAll();
    parkingSlotTypeRepository.deleteAll();
    parkingFeeRepository.deleteAll();
    carRepository.deleteAll();
  }

  @AfterEach
  void tearDown() {
    billRepository.deleteAll();
    visitHistoryRepository.deleteAll();
    parkingRepository.deleteAll();
    parkingSlotRepository.deleteAll();
    parkingSlotTypeRepository.deleteAll();
    parkingFeeRepository.deleteAll();
    carRepository.deleteAll();
  }
}
