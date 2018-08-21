package com.parking.app.service;

import com.parking.app.domain.CheckIn;
import com.parking.app.domain.DaySchedule;
import com.parking.app.domain.GetHelper;
import com.parking.app.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class CheckInHelperService {


    @Autowired
    UserServiceImpl    userService ;

    @Autowired
    DayScheduleService dayScheduleService;

    @Autowired
    ParkingSpotService parkingSpotService;


    public void checkIn1(CheckIn checkIn){
        DaySchedule daySchedule = new DaySchedule();
        daySchedule.setParkingSpotId(checkIn.getParkingSpotId());
        daySchedule.setUserEmail(checkIn.getUserEmail());

        //set endHour in daySchedule
        LocalTime endHour=null;

        endHour = LocalTime.parse(checkIn.getEndHour());
        System.out.println(endHour);
        daySchedule.setEndHour(endHour);

        daySchedule.setStartHour(LocalTime.now());
        daySchedule.setDate(LocalDate.now());

        dayScheduleService.saveEntryToDb(daySchedule);
        parkingSpotService.setParkingSpotBusy(checkIn.getParkingSpotId());



    }

    public Cookie checkin(GetHelper getHelper) {

        DaySchedule daySchedule = new DaySchedule();
        User user = new User();

        user.setUserEmail(getHelper.getUserEmail());
        user.setUserPassword(getHelper.getUserPassword());
        if (userService.verifyUser(user)) {


            //set userEmail in daySchedule
            daySchedule.setUserEmail(getHelper.getUserEmail());


            //set endHour in daySchedule
            LocalTime endHour=null;

            endHour = LocalTime.parse(getHelper.getEndHour());
            daySchedule.setEndHour(endHour);

            daySchedule.setStartHour(LocalTime.now());


            //set parkingSpotId in daySchedule
            daySchedule.setParkingSpotId(getHelper.getParkingSpotId());




            //set Date in daySchedule
            daySchedule.setDate(LocalDate.now());

            dayScheduleService.saveEntryToDb(daySchedule);

            //update parking status
            parkingSpotService.setParkingSpotBusy(getHelper.getParkingSpotId());
            //parkingSpotService.updateStatus(parkingSpotService.findParkingSpotByParkingSpotId(getHelper.getParkingSpotId()));
            String cookieVal =userService.passHash(getHelper.getUserEmail());
            Cookie userCookie = new Cookie("customCookie",cookieVal);
            user.setCookie(cookieVal);
            userService.saveUserToDb(user);
            return userCookie;
        }

        return null;
    }

}
