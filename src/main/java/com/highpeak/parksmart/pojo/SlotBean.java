package com.highpeak.parksmart.pojo;

public class SlotBean
{
    private int slotId;
    private Integer parkingAreaId;
    private Double slotLat;
    private Double slotLong;

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

    public Double getSlotLat() {
        return slotLat;
    }

    public void setSlotLat(Double slotLat) {
        this.slotLat = slotLat;
    }

    public Double getSlotLong() {
        return slotLong;
    }

    public void setSlotLong(Double slotLong) {
        this.slotLong = slotLong;
    }
}
