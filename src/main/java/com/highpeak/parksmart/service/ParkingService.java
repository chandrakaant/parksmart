package com.highpeak.parksmart.service;

import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.ParkingBean;
import com.highpeak.parksmart.pojo.SlotBean;

public interface ParkingService
{
    /**
     * Service to start parking
     *
     * @param parkingBean
     * @return
     */

    ParkingBean startParking(ParkingBean parkingBean) throws DataException;

    /**
     * Service to stop parking
     *
     * @param parkingBean
     * @return
     */

    ParkingBean stopParking(ParkingBean parkingBean) throws DataException;

    /**
     * Service to find car
     *
     * @param parkingBean
     * @return
     */

    SlotBean findCar(ParkingBean parkingBean) throws DataException;
}
