package com.ckp.parksmart.datastore.model;

import javax.persistence.*;

@Entity
@Table(name="ps_parking_area")
public class ParkingAreaModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="parking_area_id")
    private int parkingAreaId;

    @Column(name="parking_area_lat")
    private Double parkingAreaLat;

    @Column(name="parking_area_long")
    private Double parkingAreaLong;

    @Column(name="parking_area")
    private String parkingArea;

    @Column(name="parking_Area_is_active")
    private boolean parkingAreaIsActive;

    public int getParkingAreaId() {
        return parkingAreaId;
    }

    public void setParkingAreaId(int parkingAreaId) {
        this.parkingAreaId = parkingAreaId;
    }

    public String getParkingArea() {
        return parkingArea;
    }

    public void setParkingArea(String parkingArea) {
        this.parkingArea = parkingArea;
    }

    public Double getParkingAreaLat() {
        return parkingAreaLat;
    }

    public void setParkingAreaLat(Double parkingAreaLat) {
        this.parkingAreaLat = parkingAreaLat;
    }

    public Double getParkingAreaLong() {
        return parkingAreaLong;
    }

    public void setParkingAreaLong(Double parkingAreaLong) {
        this.parkingAreaLong = parkingAreaLong;
    }

    public boolean getParkingAreaIsActive() {
        return parkingAreaIsActive;
    }

    public void setParkingAreaIsActive(boolean parkingAreaIsActive) {
        this.parkingAreaIsActive = parkingAreaIsActive;
    }
}
