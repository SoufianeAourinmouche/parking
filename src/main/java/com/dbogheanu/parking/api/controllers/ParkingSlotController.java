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

import com.dbogheanu.parking.api.models.entities.ParkingSlot;
import com.dbogheanu.parking.api.servicies.ParkingSlotService;

@RestController
@RequestMapping(value = "parking/parkingSlot")
public class ParkingSlotController
{
    protected final ParkingSlotService parkingService;

    public ParkingSlotController(ParkingSlotService parkingService)
    {
        this.parkingService = parkingService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Iterable<ParkingSlot> get()
    {
        return parkingService.getAllEntities();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ParkingSlot get(@PathVariable(name = "id") int id)
    {
        return parkingService.getEntity(id);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public ParkingSlot save(@RequestBody ParkingSlot parkingSlot)
    {
        return parkingService.saveEntity(parkingSlot);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public boolean delete(@PathVariable(name = "id") int id)
    {
        return parkingService.deleteEntity(parkingService.getEntity(id));
    }
}
