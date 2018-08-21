package com.parking.app.service;

import com.parking.app.domain.DaySchedule;

public interface DayScheduleService {
    void saveEntryToDb(DaySchedule daySchedule);
    void removeByUserEmail(String userEmail);
    DaySchedule findByUserEmail(String userEmail);
    Iterable<DaySchedule> findAll();

//    DaySchedule addEntry (String email,
//                          Integer parkingSpot,
//                          Date startHour,
//                          Date endHour);
//    void updateEndHour (Date endHour);
    DaySchedule findDayScheduleByParkingSpotId(Integer parkingSpotId);



}
