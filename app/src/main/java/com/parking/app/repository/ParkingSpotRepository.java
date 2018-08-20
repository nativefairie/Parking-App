package com.parking.app.repository;


import com.parking.app.domain.ParkingSpot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ParkingSpotRepository extends CrudRepository<ParkingSpot,Integer> {
    public Iterable<ParkingSpot> findAllByParkingSpotId(Integer parkingSpotId);
    public Optional<ParkingSpot> findParkingSpotByParkingSpotId(Integer parkingSpotId);

}
