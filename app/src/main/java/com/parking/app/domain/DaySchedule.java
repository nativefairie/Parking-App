package com.parking.app.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "day_schedule")
public class DaySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEntry;

    @Column
    private LocalDate date;

    @Column
    private LocalTime startHour;

    @Column
    private LocalTime endHour;

    @Column
    private Integer parkingSpotId;

    @Column
    private String userEmail;

    public Long getIdEntry() {
        return idEntry;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    public Integer getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(Integer parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "daySchedule{" +
                "id=" + idEntry +
                ", date=" + date +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                ", parkingSpot=" + parkingSpotId +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}