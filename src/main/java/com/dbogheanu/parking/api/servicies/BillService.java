package com.dbogheanu.parking.api.servicies;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.dbogheanu.parking.api.models.entities.Bill;
import com.dbogheanu.parking.api.models.entities.Parking;
import com.dbogheanu.parking.api.models.entities.ParkingSlot;
import com.dbogheanu.parking.api.models.entities.VisitHistory;
import com.dbogheanu.parking.api.repositories.IBillRepository;
import com.dbogheanu.parking.api.servicies.interfaces.IService;

@Component
public class BillService implements IService<Bill> {

  protected final IBillRepository billRepository;
  protected final ParkingSlotService parkingSlotService;
  protected final VisitHistoryService visitHistoryService;

  public BillService(IBillRepository billRepository,
                     ParkingSlotService parkingSlotService,
                     VisitHistoryService visitHistoryService)
  {
      this.billRepository = billRepository;
      this.parkingSlotService = parkingSlotService;
      this.visitHistoryService = visitHistoryService;
  }

  @Override
  public Bill getEntity(int id)
  {
      return billRepository.findById(id).orElse(null);
  }

  @Override
  public Bill saveEntity(Bill entity)
  {
      if (entity == null)
      {
          return null;
      }

      if (entity.getParkingSlot() != null)
      {
          parkingSlotService.saveEntity(entity.getParkingSlot());
      }

      if (entity.getVisitHistory() != null)
      {

          visitHistoryService.saveEntity(entity.getVisitHistory());
      }

      return billRepository.save(entity);
  }

  @Override
  public boolean deleteEntity(Bill entity)
  {
      if (entity == null)
      {
          return false;
      }

      billRepository.delete(entity);

      return billRepository.findById(entity.getId()).orElse(null) == null;
  }

  public Iterable<Bill> getAllEntities()
  {
      return billRepository.findAll();
  }

  /**
   * Make the client pay for his/her stay in the parking
   *
   * @param parking     The parking
   * @param parkingSlot The parkingSlot in which the car was parked
   * @param visitLog    The visitLog item which it's the current visit of the car and contains the entry and the exit time
   * @return The bill with the amount depending on the pricing policy
   */
  public Bill createPaymentFromVisitLogAndParkingSlot(Parking parking, ParkingSlot parkingSlot, VisitHistory visitHistory)
  {
      Bill bill = new Bill();

      bill.setAmount(parking.getPricingPolicy().getFormula()
                            .calculateParkingFee(visitHistory.getEntryTime(), visitHistory.getExitTime()));
      bill.setParkingSlot(parkingSlot);
      bill.setVisitHistory(visitHistory);
      bill.setCreated(LocalDateTime.now());

      return billRepository.save(bill);
  }
  
  
}
