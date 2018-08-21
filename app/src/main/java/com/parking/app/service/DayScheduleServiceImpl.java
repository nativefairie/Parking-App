package com.parking.app.service;


import com.parking.app.domain.DaySchedule;
import com.parking.app.repository.DayScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DayScheduleServiceImpl implements DayScheduleService{

    @Autowired
    private DayScheduleRepository dayScheduleRepository;

    @Override

    public void saveEntryToDb (DaySchedule daySchedule){
        dayScheduleRepository.save(daySchedule);
    }

    @Override
    @Transactional
    public void removeByUserEmail (String userEmail){
        dayScheduleRepository.deleteDayScheduleByUserEmail(userEmail);
    }

    @Override
    public DaySchedule findByUserEmail (String userEmail){
        return dayScheduleRepository.findByUserEmail(userEmail);
    }

//    public DaySchedule addEntry (String email,
//                          Integer parkingSpot,
//                          Date startHour,
//                          Date endHour){
//
//    }

//    @Override
//    public void updateEndHour (Date endHour){
//        dayScheduleRepository.updateByEndHour(endHour);
//    }

    public DaySchedule findDayScheduleByParkingSpotId (Integer id){
        return dayScheduleRepository.findDayScheduleByParkingSpotId(id);
    }

    @Override
    public Iterable<DaySchedule> findAll(){
        return dayScheduleRepository.findAll();
    }



}
