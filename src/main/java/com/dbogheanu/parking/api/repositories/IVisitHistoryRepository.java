package com.dbogheanu.parking.api.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dbogheanu.parking.api.models.entities.VisitHistory;

public interface IVisitHistoryRepository extends CrudRepository<VisitHistory, Integer> {

  /**
   * Find the latest visit of a car that has not yet finished
   *
   * @param parkingId The parking id
   * @param carId     The car id
   * @return The VisitHisotry with exit time null that this car has made
   */
  @Query("SELECT vh FROM VisitHistory vh WHERE vh.parking.id = :parkingId AND vh.car.id = :carId AND vh.exitTime IS NULL")
  VisitHistory findLatestVisitByCarId(@Param(value = "parkingId") int parkingId, @Param(value = "carId") int carId);
  
}
