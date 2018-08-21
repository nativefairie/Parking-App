package com.parking.app.service;


import com.parking.app.domain.History;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface HistoryService {
   void createHistory(String userEmail);
   List<History> findAll();
}
