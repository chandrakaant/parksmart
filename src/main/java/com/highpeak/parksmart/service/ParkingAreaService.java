package com.highpeak.parksmart.service;

import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.ParkingAreaBean;

import java.util.List;

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

    ParkingAreaBean fetchParkingArea(ParkingAreaBean parkingAreaBean) throws DataException;

    List<ParkingAreaBean> fetchAllParkingArea() throws DataException;

}
