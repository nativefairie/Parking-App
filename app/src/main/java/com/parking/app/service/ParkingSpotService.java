package com.parking.app.service;

import com.parking.app.domain.DaySchedule;
import com.parking.app.domain.ParkingSpot;

public interface ParkingSpotService {
    Iterable<ParkingSpot> findAllByParkingSpotId(Integer parkingSpotId);
    Iterable<ParkingSpot> findAll();
    boolean verifyIfUnavailable(Integer parkingSpotId);
    Integer setStatusOfParked(Integer parkingSpotId);
    ParkingSpot findParkingSpotByParkingSpotId(Integer parkingSpotId);
    void setParkingSpotFree(Integer parkingSpotId);
//    void updateStatus(ParkingSpot parkingSpot);
    public void setParkingSpotBusy(Integer parkingSpotId);
    public void setParkingSpotMaybe(Integer parkingSpotId);
    public void setParkingSpotUnavailable(Integer parkingSpotId);
    boolean checkIfParkingSpotIsOrange(DaySchedule daySchedule);
}
