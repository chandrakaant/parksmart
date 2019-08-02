package com.ckp.parksmart.service;

import com.ckp.parksmart.exception.DataException;
import com.ckp.parksmart.pojo.ParkingBean;

public interface ParkingService
{
    ParkingBean startParking(ParkingBean parkingBean, int userId) throws DataException;

    ParkingBean stopParking(ParkingBean parkingBean, int userId) throws DataException;

    ParkingBean findCar(ParkingBean parkingBean, int userId) throws DataException;
}
