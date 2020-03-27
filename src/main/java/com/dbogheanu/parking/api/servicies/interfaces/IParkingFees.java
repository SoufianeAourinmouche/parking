package com.dbogheanu.parking.api.servicies.interfaces;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IParkingFees extends Serializable {

  float calculateParkingFee(LocalDateTime entryTime, LocalDateTime exitTime);
}
