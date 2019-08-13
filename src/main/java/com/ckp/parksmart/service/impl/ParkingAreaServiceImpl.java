package com.ckp.parksmart.service.impl;

import com.ckp.parksmart.datastore.model.ParkingAreaModel;
import com.ckp.parksmart.datastore.model.UserModel;
import com.ckp.parksmart.datastore.repository.ParkingAreaRepository;
import com.ckp.parksmart.datastore.repository.UserRepository;
import com.ckp.parksmart.exception.DataException;
import com.ckp.parksmart.pojo.ParkingAreaBean;
import com.ckp.parksmart.service.ParkingAreaService;
import com.ckp.parksmart.util.Constants;
import com.ckp.parksmart.util.MessageBundleResource;
import com.ckp.parksmart.util.NullEmptyUtils;
import com.ckp.parksmart.util.ValidationHelper;
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
    private ParkingAreaRepository parkingAreaRepository;

    @Autowired
    private MessageBundleResource messageBundleResource;

    @Autowired
    private UserRepository userRepository;

    /**
     * service to store parking area details
     *
     * @param parkingAreaBean
     * @return
     * @throws DataException
     */

    @Override
    public ParkingAreaBean registerParkingArea(ParkingAreaBean parkingAreaBean, int userId) throws DataException
    {
        try
        {
            Optional<UserModel> userModelOptional = userRepository.findById(userId);
            ValidationHelper.checkUserById(userModelOptional);

            if(NullEmptyUtils.isNullorEmpty(parkingAreaBean.getParkingAreaLat()) && NullEmptyUtils.isNullorEmpty(parkingAreaBean.getParkingAreaLong()))
            {
                throw new DataException(Constants.EXCEPTION,messageBundleResource.getMessage(Constants.EMPTY_FIELD),HttpStatus.BAD_REQUEST);
            }

            ParkingAreaModel parkingAreaModel = new ParkingAreaModel();

            parkingAreaModel.setParkingArea(parkingAreaBean.getParkingArea());
            parkingAreaModel.setParkingAreaLat(parkingAreaBean.getParkingAreaLat());
            parkingAreaModel.setParkingAreaLong(parkingAreaBean.getParkingAreaLong());
            parkingAreaModel.setParkingAreaIsActive(true);

            return setParkingAreaBean(parkingAreaRepository.save(parkingAreaModel));

        }
        catch (DataException e)
        {
            throw e;

        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
            throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage(Constants.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * service to fetch parking area by id
     *
     * @param parkingAreaBean
     * @return
     * @throws DataException
     */

    @Override
    public ParkingAreaBean fetchParkingArea(ParkingAreaBean parkingAreaBean, int userId) throws DataException
    {

        try
        {
            Optional<UserModel> userModelOptional = userRepository.findById(userId);
            ValidationHelper.checkUserById(userModelOptional);

            Optional<ParkingAreaModel> parkingAreaModel = parkingAreaRepository.findByParkingAreaIdAndParkingAreaIsActiveTrue(parkingAreaBean.getParkingAreaId());

            if(!parkingAreaModel.isPresent())
            {
                throw new DataException(Constants.EXCEPTION,messageBundleResource.getMessage(Constants.PARKING_AREA_DOESNT_EXIST), HttpStatus.BAD_REQUEST);
            }

            return setParkingAreaBean(parkingAreaModel.get());

        }
        catch (DataException e)
        {
            throw e;

        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
            throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage(Constants.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * service to fetch all
     *
     * @return
     * @throws DataException
     */

    @Override
    public List<ParkingAreaBean> fetchAllParkingArea(int userId) throws DataException {

        try
        {
            Optional<UserModel> userModelOptional = userRepository.findById(userId);
            ValidationHelper.checkUserById(userModelOptional);

            List<ParkingAreaBean> parkingAreaBeanList = new ArrayList<>();
            List<ParkingAreaModel> parkingAreaModelList = new ArrayList<>();

            parkingAreaModelList = parkingAreaRepository.findByParkingAreaIsActiveTrue();

            for(ParkingAreaModel parkingAreaModel : parkingAreaModelList)
            {
                parkingAreaBeanList.add(setParkingAreaBean(parkingAreaModel));
            }

            return parkingAreaBeanList;

        }
        catch (DataException e)
        {
            throw e;

        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
            throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage(Constants.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
