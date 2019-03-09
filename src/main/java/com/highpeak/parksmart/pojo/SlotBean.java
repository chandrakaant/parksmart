package com.highpeak.parksmart.pojo;

public class SlotBean
{
    private int slotId;
    private Integer parkingAreaId;
    private String slotLocation;

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public Integer getParkingAreaId() {
        return parkingAreaId;
    }

    public void setParkingAreaId(Integer parkingAreaId) {
        this.parkingAreaId = parkingAreaId;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }
}
