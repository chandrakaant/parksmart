package com.highpeak.parksmart.service;

import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.ParkingAreaBean;

public interface ParkingAreaService
{
    /**
     * service to store parking area details
     *
     * @param parkingAreaBean
     * @return
     * @throws DataException
     */

    ParkingAreaBean registerParkingArea(ParkingAreaBean parkingAreaBean) throws DataException;
}
