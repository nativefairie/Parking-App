package com.parking.app.service;

import com.parking.app.domain.DaySchedule;
import com.parking.app.domain.ParkingSpot;
import com.parking.app.repository.DayScheduleRepository;
import com.parking.app.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalTime;

@Service
@Transactional
public class ParkingSpotServiceImpl implements ParkingSpotService{

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public ParkingSpot findParkingSpotByParkingSpotId(Integer parkingSpotId){
        return parkingSpotRepository.findParkingSpotByParkingSpotId(parkingSpotId).get();
    }

    public Iterable<ParkingSpot> findAllByParkingSpotId (Integer parkingSpotId){
        return parkingSpotRepository.findAllByParkingSpotId(parkingSpotId);
    }

//    public void updateStatus(ParkingSpot parkingSpot){
//            Integer id = parkingSpot.getParkingSpotId();
//            if(!verifyIfUnavailable(id))
//                //parkingList.iterator().next().setStatus(setStatusOfParked(id));
//                parkingSpot.setStatus(setStatusOfParked(id));
//
//
//
//
//
//    }


    public Iterable<ParkingSpot> findAll(){
        Iterable<ParkingSpot> parkingList = parkingSpotRepository.findAll();
        for (ParkingSpot parkingSpot : parkingList) {
            Integer id = parkingSpot.getParkingSpotId();
            if(!verifyIfUnavailable(id))

                parkingSpot.setStatus(setStatusOfParked(id));



        }
        return parkingList;
    }

    @Override
    public boolean verifyIfUnavailable(Integer parkingSpotId) {


        if(parkingSpotRepository.findById(parkingSpotId).get().getStatus()==2)
            return true;
        return false;

    }

    @Override
    public boolean checkIfParkingSpotIsOrange(DaySchedule daySchedule){
        LocalTime currentHour=LocalTime.now();
        LocalTime endHour = daySchedule.getEndHour();
//        long diff = Math.abs((endHour.getTime() - currentDate.getTime())/60000);
//        System.out.println("diff"+ diff);

        if(Math.abs(Duration.between(currentHour, endHour).toMinutes())<15) return true;
        else return false;

    }





    @Override
    public void setParkingSpotFree(Integer parkingSpotId){

        ParkingSpot parkingSpot = findParkingSpotByParkingSpotId(parkingSpotId);
        parkingSpot.setStatus(0);
    }

    @Override
    public void setParkingSpotBusy(Integer parkingSpotId){

        ParkingSpot parkingSpot = findParkingSpotByParkingSpotId(parkingSpotId);
        parkingSpot.setStatus(1);

    }

    @Override
    public void setParkingSpotMaybe(Integer parkingSpotId){

        ParkingSpot parkingSpot = findParkingSpotByParkingSpotId(parkingSpotId);
        parkingSpot.setStatus(3);
    }

    @Override
    public void setParkingSpotUnavailable(Integer parkingSpotId) {

        ParkingSpot parkingSpot = findParkingSpotByParkingSpotId(parkingSpotId);
        parkingSpot.setStatus(2);
    }





    @Autowired
    private DayScheduleRepository dayScheduleRepository;

    @Override
    public Integer setStatusOfParked(Integer parkingSpotId) {
        Integer status=0;
        DaySchedule daySchedule=null;
        daySchedule = dayScheduleRepository.findDayScheduleByParkingSpotId(parkingSpotId);
        if(daySchedule!=null)
            { //status=busy
            if(checkIfParkingSpotIsOrange(daySchedule))
                status=3; //status=orange
            else
                status=1;
            }
        return status;
    }

}
