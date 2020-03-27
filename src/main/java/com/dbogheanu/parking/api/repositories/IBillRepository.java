package com.dbogheanu.parking.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.dbogheanu.parking.api.models.entities.Bill;

public interface IBillRepository extends CrudRepository<Bill, Integer> {

}
