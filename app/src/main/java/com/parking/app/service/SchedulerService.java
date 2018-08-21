package com.parking.app.service;

import com.parking.app.domain.DaySchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerService {

    @Autowired
    DayScheduleService dayScheduleService;

    @Autowired
    CheckOutHelperService checkOutHelperService;

    @Scheduled(	cron = "0 11 0 1/1 * ?")
    public void cleanDayScheduleAtTheEndOfTheDay() {
        Iterable<DaySchedule> leftEntries = dayScheduleService.findAll();
        for (DaySchedule leftEntry : leftEntries) {
            checkOutHelperService.checkOut(leftEntry.getUserEmail());

        }


    }

//    @Scheduled
//    public void

}
