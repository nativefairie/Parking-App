package com.parking.app.service;


import com.parking.app.domain.DaySchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckOutHelperService {

    @Autowired
    HistoryService historyService;

    @Autowired
    ParkingSpotService parkingSpotService ;

    @Autowired
    DayScheduleService dayScheduleService;

    public void checkOut (String userEmail){
        //cream o noua instrare in history cu o noua endHour
        historyService.createHistory(userEmail);


        //setam statusul parkingSpot ca free=0
        DaySchedule daySchedule = dayScheduleService.findByUserEmail(userEmail);
        Integer parkingSpotId = daySchedule.getParkingSpotId();
        parkingSpotService.setParkingSpotFree(parkingSpotId);

        //stergem intrarea din daySchedule
        dayScheduleService.removeByUserEmail(userEmail);


    }
}
