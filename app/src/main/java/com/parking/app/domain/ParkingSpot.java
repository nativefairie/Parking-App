package com.parking.app.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "parking_spots")
public class ParkingSpot {

    @Id
    private Integer parkingSpotId;

    @Column
    private Integer status;

    public Integer getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(Integer parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
