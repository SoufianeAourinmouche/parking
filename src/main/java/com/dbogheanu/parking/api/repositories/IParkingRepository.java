package com.dbogheanu.parking.api.repositories;

import org.springframework.data.repository.CrudRepository;
import com.dbogheanu.parking.api.models.entities.Parking;

public interface IParkingRepository extends CrudRepository<Parking, Integer>
{
}

