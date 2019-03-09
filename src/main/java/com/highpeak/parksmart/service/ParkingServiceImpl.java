package com.highpeak.parksmart.service;

import com.highpeak.parksmart.datastore.model.ParkingModel;
import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.ParkingBean;
import org.springframework.stereotype.Service;

@Service
public class ParkingServiceImpl implements ParkingService
{
    @Override
    public ParkingBean startParking(ParkingBean parkingBean) throws DataException {
        return null;
    }

    @Override
    public ParkingBean stopParking(ParkingBean parkingBean) throws DataException {
        return null;
    }

    @Override
    public ParkingBean findCar(ParkingBean parkingBean) throws DataException {
        return null;
    }

    /**
     * private method to set parkingBean
     *
     * @param parkingModel
     * @return
     */

    private ParkingBean setParkingBean(ParkingModel parkingModel)
    {
        ParkingBean parkingBean = new ParkingBean();

        parkingBean.setParkingId(parkingModel.getParkingId());
        parkingBean.setVehicleId(parkingModel.getVehicleId());
        parkingBean.setSlotId(parkingModel.getSlotId());
        parkingBean.setStartTime(parkingModel.getStartTime());
        parkingBean.setEndTime(parkingModel.getEndTime());
        parkingBean.setAmount(parkingModel.getAmount());

        return parkingBean;
    }
}
