package com.parking.app.service;

import com.parking.app.domain.ParkingPackage;
import com.parking.app.domain.ParkingSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ParkingPackageHelper {

    @Autowired
    DayScheduleService dayScheduleService;

    @Autowired
    ParkingSpotService parkingSpotService;

    public List<ParkingPackage>packForParking()

    {

       // ParkingPackage parkingPackage = new ParkingPackage();
        List<ParkingPackage> showList = new LinkedList<>();

        Iterable<ParkingSpot> parkingSpotIterable = parkingSpotService.findAll();

        for (ParkingSpot ps : parkingSpotIterable){
            ParkingPackage parkingPackage = new ParkingPackage();
            parkingPackage.setParkingSpotId(ps.getParkingSpotId());
            parkingPackage.setStatus(ps.getStatus());

        //    String userEmail = new String();
            if(ps.getStatus()==1 || ps.getStatus()==3)
                parkingPackage.setUserEmail(dayScheduleService.findDayScheduleByParkingSpotId(ps.getParkingSpotId()).getUserEmail());
    //        else parkingPackage.setUserEmail(" ");

//            if((userEmail = dayScheduleService.findDayScheduleByParkingSpotId(ps.getParkingSpotId()).getUserEmail())!=null)
//                parkingPackage.setUserEmail(userEmail);
//            else parkingPackage.setUserEmail("");
            showList.add(parkingPackage);
        }

        return showList;
    }


}
