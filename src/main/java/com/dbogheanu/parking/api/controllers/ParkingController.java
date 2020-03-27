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

import com.dbogheanu.parking.api.models.entities.Parking;
import com.dbogheanu.parking.api.servicies.ParkingService;

@RestController
@RequestMapping(value = "parking")
public class ParkingController
{
    protected final ParkingService parkingService;

    public ParkingController(ParkingService parkingService)
    {
        this.parkingService = parkingService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Iterable<Parking> get()
    {
        return parkingService.getAllEntities();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Parking get(@PathVariable(name = "id") int id)
    {
        return parkingService.getEntity(id);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public Parking save(@RequestBody Parking parking)
    {
        return parkingService.saveEntity(parking);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public boolean delete(@PathVariable(name = "id") int id)
    {
        return parkingService.deleteEntity(parkingService.getEntity(id));
    }
}    