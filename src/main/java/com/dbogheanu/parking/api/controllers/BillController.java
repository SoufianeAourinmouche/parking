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

import com.dbogheanu.parking.api.models.entities.Bill;
import com.dbogheanu.parking.api.models.entities.Parking;
import com.dbogheanu.parking.api.models.entities.ParkingSlot;
import com.dbogheanu.parking.api.models.entities.VisitHistory;
import com.dbogheanu.parking.api.servicies.BillService;
import com.dbogheanu.parking.api.servicies.ParkingService;
import com.dbogheanu.parking.api.servicies.ParkingSlotService;
import com.dbogheanu.parking.api.servicies.VisitHistoryService;
import com.dbogheanu.parking.api.servicies.dto.BillDto;

@RestController
@RequestMapping(value = "parking/bill")
public class BillController
{
    protected final BillService billService;
    protected final VisitHistoryService visitHistoryService;
    protected final ParkingService parkingService;
    protected final ParkingSlotService parkingSlotService;

    public BillController(BillService billService,
                          VisitHistoryService visitHistoryService,
                          ParkingService parkingService,
                          ParkingSlotService parkingSlotService)
    {
        this.billService = billService;
        this.visitHistoryService = visitHistoryService;
        this.parkingService = parkingService;
        this.parkingSlotService = parkingSlotService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Iterable<Bill> get()
    {
        return billService.getAllEntities();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Bill get(@PathVariable(name = "id") int id)
    {
        return billService.getEntity(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Bill save(@RequestBody Bill bill)
    {
        return billService.saveEntity(bill);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public boolean delete(@PathVariable(name = "id") int id)
    {
        return billService.deleteEntity(billService.getEntity(id));
    }

    @PostMapping(value = "/payment")
    public Bill save(@RequestBody BillDto billDto)
    {
        VisitHistory visitHistory = visitHistoryService.getEntity(billDto.getVisitHistoryId());
        Parking parking = parkingService.getEntity(billDto.getParkingId());
        ParkingSlot parkingSlot = parkingSlotService.getEntity(billDto.getParkingSlotId());

        return billService.createPaymentFromVisitLogAndParkingSlot(parking, parkingSlot, visitHistory);
    }
}