package com.parking.app.service;

import com.parking.app.domain.DaySchedule;
import com.parking.app.domain.History;
import com.parking.app.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {




    @Autowired
    DayScheduleServiceImpl dayScheduleServiceImpl ;

    @Autowired
    HistoryRepository historyRepository;
    public void createHistory (String userEmail){


        LocalTime endHour = LocalTime.now();




        //get daySchedule from DB
        DaySchedule daySchedule = dayScheduleServiceImpl.findByUserEmail(userEmail);

        //set history fields

        History history = new History();
        history.setUserEmail(userEmail);
        history.setParkingSpot(daySchedule.getParkingSpotId());
        history.setStartHour(daySchedule.getStartHour());
        history.setDate(daySchedule.getDate());
        history.setEndHour(endHour);
        historyRepository.save(history);

    }

    public List<History> findAll() {
        return historyRepository.findAll();
    }
//    public Integer findAllEntriesInAWeekDay(Integer zi){
//        Iterable<History> historyIterable = historyRepository.findAll();
//        List<History> historyList = new ArrayList<>();
//        Long cnt=1L;
//        for (History history : historyIterable) {
//            historyList.add(history);
//            if(history.getDate().getDayOfWeek().equals(Calendar.MONDAY))
//            {
//                historyList.add(history);
//                }
//
//        }
//        LocalDate referinta = historyList.get(0).getDate();
//        for (int i=1;i<historyList.size();i++) {
//
//            if(!historyList.get(i).getDate().equals(referinta))
//            {
//                cnt++;
//                referinta = historyList.get(i).getDate();
//            }
//
//        }
//
//
//        return ;
//
//    }

}
