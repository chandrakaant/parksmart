package com.highpeak.parksmart.service;

import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.ParkingBean;

public interface ParkingService
{
    ParkingBean startParking(ParkingBean parkingBean) throws DataException;

    ParkingBean stopParking(ParkingBean parkingBean) throws DataException;

    ParkingBean findCar(ParkingBean parkingBean) throws DataException;
}
