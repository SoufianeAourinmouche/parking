package com.dbogheanu.parking.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.dbogheanu.parking.api.models.entities.ParkingFee;

public interface IParkingFeeRepository  extends CrudRepository<ParkingFee, Integer>{

}
