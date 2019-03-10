package com.highpeak.parksmart.service;

import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.ParkingBean;
import com.highpeak.parksmart.pojo.SlotBean;

public interface ParkingService
{
    ParkingBean startParking(ParkingBean parkingBean, int userId) throws DataException;

    ParkingBean stopParking(ParkingBean parkingBean, int userId) throws DataException;

    ParkingBean findCar(ParkingBean parkingBean, int userId) throws DataException;
}
