package com.ckp.parksmart.service;

import com.ckp.parksmart.exception.DataException;
import com.ckp.parksmart.pojo.ParkingAreaBean;

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

    ParkingAreaBean registerParkingArea(ParkingAreaBean parkingAreaBean, int userId) throws DataException;

    /**
     * service to fetch parking area by id
     *
     * @param parkingAreaBean
     * @return
     * @throws DataException
     */

    ParkingAreaBean fetchParkingArea(ParkingAreaBean parkingAreaBean, int userId) throws DataException;

    /**
     * service to fetch all
     *
     * @return
     * @throws DataException
     */

    List<ParkingAreaBean> fetchAllParkingArea(int userId) throws DataException;

}
