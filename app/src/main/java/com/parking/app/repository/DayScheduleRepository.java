package com.parking.app.repository;

import com.parking.app.domain.DaySchedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface DayScheduleRepository extends CrudRepository<DaySchedule,Long> {
    DaySchedule findByUserEmail(String userEmail);

    void deleteDayScheduleByUserEmail(String userEmail);

    DaySchedule findDayScheduleByParkingSpotId(Integer parkingSpotId);







}
