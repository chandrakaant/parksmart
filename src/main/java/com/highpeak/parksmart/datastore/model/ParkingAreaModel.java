package com.highpeak.parksmart.datastore.model;

import javax.persistence.*;

@Entity
@Table(name="ps_parking_area")
public class ParkingAreaModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="parking_area_id")
    private int parkingAreaId;

    @Column(name="parking_area_location")
    private String parkingAreaLocation;

    @Column(name="parking_area")
    private String parkingArea;

    public int getParkingAreaId() {
        return parkingAreaId;
    }

    public void setParkingAreaId(int parkingAreaId) {
        this.parkingAreaId = parkingAreaId;
    }

    public String getParkingAreaLocation() {
        return parkingAreaLocation;
    }

    public void setParkingAreaLocation(String parkingAreaLocation) {
        this.parkingAreaLocation = parkingAreaLocation;
    }

    public String getParkingArea() {
        return parkingArea;
    }

    public void setParkingArea(String parkingArea) {
        this.parkingArea = parkingArea;
    }
}
