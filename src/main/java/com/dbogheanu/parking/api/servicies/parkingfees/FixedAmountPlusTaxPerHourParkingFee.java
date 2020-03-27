package com.dbogheanu.parking.api.servicies.parkingfees;

import java.time.Duration;
import java.time.LocalDateTime;

import com.dbogheanu.parking.api.servicies.interfaces.IParkingFees;

public class FixedAmountPlusTaxPerHourParkingFee implements IParkingFees {

  private float fixedAmount;

  private float pricePerHour;

  public FixedAmountPlusTaxPerHourParkingFee(float pricePerHour, float fixedAmount)
  {
      this.pricePerHour = pricePerHour;
      this.fixedAmount = fixedAmount;
  }

  public float getFixedAmount()
  {
      return fixedAmount;
  }

  public void setFixedAmount(float fixedAmount)
  {
      this.fixedAmount = fixedAmount;
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
  public float calculateParkingFee(LocalDateTime entryTime, LocalDateTime exitTime)
  {
      if (entryTime == null || exitTime == null)
      {
          throw new IllegalArgumentException("Cannot be null");
      }

      return Duration.between(entryTime, exitTime).toHours() * pricePerHour + fixedAmount;
  }
}
