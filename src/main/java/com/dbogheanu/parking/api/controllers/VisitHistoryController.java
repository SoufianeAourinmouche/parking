package com.dbogheanu.parking.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dbogheanu.parking.api.models.entities.VisitHistory;
import com.dbogheanu.parking.api.models.enums.EParkingSlotType;
import com.dbogheanu.parking.api.servicies.VisitHistoryService;
import com.dbogheanu.parking.api.servicies.dto.VisitParkingDto;

@RestController
@RequestMapping(value = "parking/visitHistory")
public class VisitHistoryController {

  protected final VisitHistoryService visitHistoryService;

  public VisitHistoryController(VisitHistoryService visitHistoryService)
  {
      this.visitHistoryService = visitHistoryService;
  }

  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  public Iterable<VisitHistory> get()
  {
      return visitHistoryService.getAllEntities();
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public VisitHistory get(@PathVariable(name = "id") int id)
  {
      return visitHistoryService.getEntity(id);
  }

  @PostMapping()
  @ResponseStatus(value = HttpStatus.OK)
  public VisitHistory save(@RequestBody VisitHistory visitLog)
  {
      return visitHistoryService.saveEntity(visitLog);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public boolean delete(@PathVariable(name = "id") int id)
  {
      return visitHistoryService.deleteEntity(visitHistoryService.getEntity(id));
  }

  @PostMapping(value = "/checkIn")
  @ResponseStatus(value = HttpStatus.OK)
  public VisitHistory checkIn(@RequestBody VisitParkingDto visitDto)
  {
      return visitHistoryService.checkIn(visitDto.getParkingId(), visitDto.getRegistrationPlate(),
                                     EParkingSlotType.valueOf(visitDto.getParkingSlotTypeName()));
  }

  @PostMapping(value = "/checkOut")
  @ResponseStatus(value = HttpStatus.OK)
  public VisitHistory checkOut(@RequestBody VisitParkingDto visitDto)
  {
      return visitHistoryService.checkOut(visitDto.getParkingId(), visitDto.getRegistrationPlate());
  }
  
}
