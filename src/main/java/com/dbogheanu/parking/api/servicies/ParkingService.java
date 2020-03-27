package com.dbogheanu.parking.api.servicies;

import org.springframework.stereotype.Component;

import com.dbogheanu.parking.api.models.entities.Parking;
import com.dbogheanu.parking.api.repositories.IParkingRepository;
import com.dbogheanu.parking.api.servicies.interfaces.IService;

@Component
public class ParkingService implements IService<Parking>
{
    protected final IParkingRepository parkingRepository;
    protected final ParkingFeeService parkingFeeService;

    public ParkingService(IParkingRepository parkingRepository, ParkingFeeService parkingFeeService)
    {
        this.parkingRepository = parkingRepository;
        this.parkingFeeService = parkingFeeService;
    }

    @Override
    public Parking getEntity(int id)
    {
        return parkingRepository.findById(id).orElse(null);
    }

    @Override
    public Parking saveEntity(Parking entity)
    {
        if (entity == null)
        {
            return null;
        }

        parkingFeeService.saveEntity(entity.getPricingPolicy());
        return parkingRepository.save(entity);
    }

    @Override
    public boolean deleteEntity(Parking entity)
    {
        if (entity == null)
        {
            return false;
        }

        parkingRepository.delete(entity);

        return parkingRepository.findById(entity.getId()).orElse(null) == null;
    }

    public Iterable<Parking> getAllEntities()
    {
        return parkingRepository.findAll();
    }
}