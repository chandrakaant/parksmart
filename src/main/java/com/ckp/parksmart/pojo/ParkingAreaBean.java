package com.ckp.parksmart.pojo;

public class ParkingAreaBean
{
    private int parkingAreaId;
    private String parkingArea;
    private Double parkingAreaLat;
    private Double parkingAreaLong;
    private String parkingAreaIsActive;

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

    public String getParkingAreaIsActive() {
        return parkingAreaIsActive;
    }

    public void setParkingAreaIsActive(String parkingAreaIsActive) {
        this.parkingAreaIsActive = parkingAreaIsActive;
    }
}
