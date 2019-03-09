package com.highpeak.parksmart.pojo;

public class VehicleBean {

    private int id;
    private String name;
    private String number;
    private String location;
    private String manufacturerName;
    private Integer userId;
    private boolean isActive;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
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
