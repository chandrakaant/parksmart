package com.ckp.parksmart.pojo;

public class ParkingBean
{
    private int parkingId;
    private Integer vehicleId;
    private Integer slotId;
    private Long startTime;
    private Long endTime;
    private boolean parkingIsActive;
    private String amount;

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public boolean isParkingIsActive() {
        return parkingIsActive;
    }

    public void setParkingIsActive(boolean parkingIsActive) {
        this.parkingIsActive = parkingIsActive;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
