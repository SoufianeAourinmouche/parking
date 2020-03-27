package com.dbogheanu.parking.api.servicies.parkingfees;

import java.time.Duration;
import java.time.LocalDateTime;

import com.dbogheanu.parking.api.servicies.interfaces.IParkingFees;

public class TaxPerHourParkingFee implements IParkingFees {

  private float pricePerHour;

  public TaxPerHourParkingFee(float pricePerHour)
  {
      this.pricePerHour = pricePerHour;
  }

  public float getPricePerHour()
  {
      return pricePerHour;
  }

  public void setPricePerHour(float pricePerHour)
  {
      this.pricePerHour = pricePerHour;
  }
  
  @Override
  public float calculateParkingFee(LocalDateTime entryTime, LocalDateTime exitTime) {
    if (entryTime == null || exitTime == null)
    {
        throw new IllegalArgumentException("Cannot be null");
    }

    return Duration.between(entryTime, exitTime).toHours() * pricePerHour;
  }

}
