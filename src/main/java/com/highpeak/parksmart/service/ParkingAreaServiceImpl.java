package com.highpeak.parksmart.service;

import com.highpeak.parksmart.datastore.model.ParkingAreaModel;
import com.highpeak.parksmart.datastore.repository.ParkingAreaRepository;
import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.ParkingAreaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingAreaServiceImpl implements ParkingAreaService
{
    private static Logger LOGGER = LoggerFactory.getLogger(ParkingAreaServiceImpl.class);

    @Autowired
    ParkingAreaRepository parkingAreaRepository;

    /**
     * service to store parking area details
     *
     * @param parkingAreaBean
     * @return
     * @throws DataException
     */

    @Override
    public ParkingAreaBean registerParkingArea(ParkingAreaBean parkingAreaBean) throws DataException
    {
        ParkingAreaModel parkingAreaModel = new ParkingAreaModel();

        parkingAreaModel.setParkingArea(parkingAreaBean.getParkingArea());
        parkingAreaModel.setParkingAreaLocation(parkingAreaBean.getParkingAreaLocation());

        return setParkingAreaBean(parkingAreaRepository.save(parkingAreaModel));
    }

    /**
     * private method to set parkingAreaBean
     *
     * @param parkingAreaModel
     * @return
     */

    private ParkingAreaBean setParkingAreaBean(ParkingAreaModel parkingAreaModel)
    {
        ParkingAreaBean parkingAreaBean = new ParkingAreaBean();

        parkingAreaBean.setParkingAreaId(parkingAreaModel.getParkingAreaId());
        parkingAreaBean.setParkingArea(parkingAreaModel.getParkingArea());
        parkingAreaBean.setParkingAreaLocation(parkingAreaModel.getParkingAreaLocation());

        return parkingAreaBean;
    }
}
