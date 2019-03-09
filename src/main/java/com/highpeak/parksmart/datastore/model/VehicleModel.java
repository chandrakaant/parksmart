package com.highpeak.parksmart.datastore.model;

import javax.persistence.*;

@Entity
@Table(name = "ps_vehicle_details")
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "v_id")
    private int id;

    @Column(name = "v_number")
    private String vehicleNumber;

    @Column(name = "v_name")
    private String vehicleName;

    @Column(name = "v_location")
    private String vehicleLocation;

    @Column(name = "v_user_id")
    private int userId;

    @Column(name = "v_manufacturer_name")
    private String manufacturerName;

    @Column(name = "v_is_active")
    private boolean isActive;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getVehicleNumber()
    {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber)
    {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleName()
    {
        return vehicleName;
    }


    public void setVehicleName(String vehicleName)
    {
        this.vehicleName = vehicleName;
    }

    public String getVehicleLocation()
    {
        return vehicleLocation;
    }

    public void setVehicleLocation(String vehicleLocation)
    {
        this.vehicleLocation = vehicleLocation;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getManufacturerName()
    {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName)
    {
        this.manufacturerName = manufacturerName;
    }

    public boolean isActive()
    {
        return isActive;
    }

    public void setActive(boolean active)
    {
        isActive = active;
    }
}
