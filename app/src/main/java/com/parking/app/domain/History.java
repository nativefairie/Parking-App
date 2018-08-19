package com.parking.app.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "history")
public class History {
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
    private Integer parkingSpot;

    @Column
    private String userEmail;

    public Long getIdEntry() {
        return idEntry;
    }

    public void setIdEntry(Long idEntry) {
        this.idEntry = idEntry;
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

    public Integer getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(Integer parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "history{" +
                "id=" + idEntry +
                ", date=" + date +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                ", parkingSpot=" + parkingSpot +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}