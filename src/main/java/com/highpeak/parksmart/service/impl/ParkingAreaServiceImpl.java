package com.highpeak.parksmart.service.impl;

import com.highpeak.parksmart.datastore.model.ParkingAreaModel;
import com.highpeak.parksmart.datastore.repository.ParkingAreaRepository;
import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.ParkingAreaBean;
import com.highpeak.parksmart.service.ParkingAreaService;
import com.highpeak.parksmart.util.Constants;
import com.highpeak.parksmart.util.MessageBundleResource;
import com.highpeak.parksmart.util.NullEmptyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingAreaServiceImpl implements ParkingAreaService
{
    private static Logger LOGGER = LoggerFactory.getLogger(ParkingAreaServiceImpl.class);

    @Autowired
    ParkingAreaRepository parkingAreaRepository;

    @Autowired
    MessageBundleResource messageBundle;

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
        if(NullEmptyUtils.isNullorEmpty(parkingAreaBean.getParkingAreaLat()) && NullEmptyUtils.isNullorEmpty(parkingAreaBean.getParkingAreaLong()))
        {
            throw new DataException(Constants.EXCEPTION,messageBundle.getMessage(Constants.EMPTY_FIELD),HttpStatus.BAD_REQUEST);
        }

        ParkingAreaModel parkingAreaModel = new ParkingAreaModel();

        parkingAreaModel.setParkingArea(parkingAreaBean.getParkingArea());
        parkingAreaModel.setParkingAreaLat(parkingAreaBean.getParkingAreaLat());
        parkingAreaModel.setParkingAreaLong(parkingAreaBean.getParkingAreaLong());
        parkingAreaModel.setParkingAreaIsActive(true);

        return setParkingAreaBean(parkingAreaRepository.save(parkingAreaModel));
    }

    /**
     * service to fetch parking area by id
     *
     * @param parkingAreaBean
     * @return
     * @throws DataException
     */

    @Override
    public ParkingAreaBean fetchParkingArea(ParkingAreaBean parkingAreaBean) throws DataException
    {
        Optional<ParkingAreaModel> parkingAreaModel = parkingAreaRepository.findByParkingAreaIdAndParkingAreaIsActiveTrue(parkingAreaBean.getParkingAreaId());

        if(!parkingAreaModel.isPresent())
        {
            throw new DataException(Constants.EXCEPTION,messageBundle.getMessage(Constants.PARKING_AREA_DOESNT_EXIST), HttpStatus.BAD_REQUEST);
        }

        return setParkingAreaBean(parkingAreaModel.get());
    }

    /**
     * service to fetch all
     *
     * @return
     * @throws DataException
     */

    @Override
    public List<ParkingAreaBean> fetchAllParkingArea() throws DataException {

        List<ParkingAreaBean> parkingAreaBeanList = new ArrayList<>();
        List<ParkingAreaModel> parkingAreaModelList = new ArrayList<>();

        parkingAreaModelList = parkingAreaRepository.findByParkingAreaIsActiveTrue();

        for(ParkingAreaModel parkingAreaModel : parkingAreaModelList)
        {
            parkingAreaBeanList.add(setParkingAreaBean(parkingAreaModel));
        }

        return parkingAreaBeanList;
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
        parkingAreaBean.setParkingAreaLat(parkingAreaModel.getParkingAreaLat());
        parkingAreaBean.setParkingAreaLong(parkingAreaModel.getParkingAreaLong());

        return parkingAreaBean;
    }


}
