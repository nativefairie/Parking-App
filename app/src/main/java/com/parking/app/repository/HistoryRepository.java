package com.parking.app.repository;

import com.parking.app.domain.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Repository
@Transactional
public interface HistoryRepository extends CrudRepository<History,Long> {
    List<History> findAllByDate(Date date);
    List<History> findAllByUserEmail(Date date);
//    List<History> findAllByDateDayOfWeekMondayIsLike();
   // List<History> findDistinctByDate_DayOfWeekMatches
    List<History> findAll();

}
