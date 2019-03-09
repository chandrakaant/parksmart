package com.highpeak.parksmart.datastore.model;

import javax.persistence.*;

@Entity
@Table(name="ps_slot")
public class SlotModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="s_id")
    private int slotId;

    @Column(name="p_id")
    private Integer parkingAreaId;

    @Column(name="s_lat")
    private Double slotLat;

    @Column(name="s_long")
    private Double slotLong;

    @Column(name="s_active")
    private boolean slotActive;

    public boolean isSlotActive() {
        return slotActive;
    }

    public void setSlotActive(boolean slotActive) {
        this.slotActive = slotActive;
    }

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
