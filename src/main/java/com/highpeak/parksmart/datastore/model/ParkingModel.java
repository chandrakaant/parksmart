package com.highpeak.parksmart.datastore.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="ps_parking")
public class ParkingModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="parking_id")
    private int parkingId;

    @Column(name="v_id")
    private Integer vehicleId;

    @Column(name="s_id")
    private Integer slotId;

    @Column(name="start_time")
    private Timestamp startTime;

    @Column(name="end_time")
    private Timestamp endTime;

    @Column(name="parking_is_active")
    private boolean parkingIsActive;

    @Column(name="amount")
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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
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
