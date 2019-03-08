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

    @Column(name="s_location")
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
