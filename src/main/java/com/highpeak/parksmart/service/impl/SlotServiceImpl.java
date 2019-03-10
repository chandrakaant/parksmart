package com.highpeak.parksmart.service.impl;

import com.highpeak.parksmart.datastore.model.ParkingAreaModel;
import com.highpeak.parksmart.datastore.model.SlotModel;
import com.highpeak.parksmart.datastore.model.UserModel;
import com.highpeak.parksmart.datastore.repository.ParkingAreaRepository;
import com.highpeak.parksmart.datastore.repository.SlotRepository;
import com.highpeak.parksmart.datastore.repository.UserRepository;
import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.SlotBean;
import com.highpeak.parksmart.service.SlotService;
import com.highpeak.parksmart.util.Constants;
import com.highpeak.parksmart.util.MessageBundleResource;
import com.highpeak.parksmart.util.NullEmptyUtils;
import com.highpeak.parksmart.util.ValidationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SlotServiceImpl implements SlotService
{
    private static Logger LOGGER = LoggerFactory.getLogger(SlotServiceImpl.class);

    @Autowired
    SlotRepository slotRepository;

    @Autowired
    ParkingAreaRepository parkingAreaRepository;

    @Autowired
    MessageBundleResource messageBundle;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageBundleResource messageBundleResource;

    /**
     * Service to register slot
     *
     * @param slotBean
     * @return
     * @throws DataException
     */

    @Override
    public SlotBean registerSlot(SlotBean slotBean, int userId) throws DataException
    {
        try
        {
            Optional<UserModel> userModelOptional = userRepository.findById(userId);
            ValidationHelper.checkUserById(userModelOptional);

            if(NullEmptyUtils.isNullorEmpty(slotBean.getParkingAreaId()) || NullEmptyUtils.isNullorEmpty(slotBean.getSlotLat()) && NullEmptyUtils.isNullorEmpty(slotBean.getSlotLong()))
            {
                throw new DataException(Constants.EXCEPTION,messageBundle.getMessage(Constants.EMPTY_FIELD),HttpStatus.BAD_REQUEST);
            }

            Optional<ParkingAreaModel> parkingAreaModel = parkingAreaRepository.findByParkingAreaIdAndParkingAreaIsActiveTrue(slotBean.getParkingAreaId());

            if(!parkingAreaModel.isPresent())
            {
                throw new DataException(Constants.EXCEPTION,messageBundle.getMessage(Constants.PARKING_AREA_DOESNT_EXIST),HttpStatus.BAD_REQUEST);
            }

            SlotModel slotModel = new SlotModel();

            slotModel.setParkingAreaId(slotBean.getParkingAreaId());
            slotModel.setSlotLat(slotBean.getSlotLat());
            slotModel.setSlotLong(slotBean.getSlotLong());
            slotModel.setSlotActive(true);

            return setSlotBean(slotRepository.save(slotModel));

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
     * Service to fetch a slot by id
     *
     * @param slotBean
     * @return
     */

    @Override
    public SlotBean fetchSlotById(SlotBean slotBean, int userId) throws DataException
    {
        try
        {
            Optional<UserModel> userModelOptional = userRepository.findById(userId);
            ValidationHelper.checkUserById(userModelOptional);

            Optional<SlotModel> slotModel = slotRepository.findBySlotIdAndSlotActiveTrue(slotBean.getSlotId());

            if(!slotModel.isPresent())
            {
                throw new DataException(Constants.EXCEPTION,messageBundle.getMessage(Constants.SLOT_DOESNT_EXIST), HttpStatus.BAD_REQUEST);
            }
            return setSlotBean(slotModel.get());
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

    @Override
    public List<SlotBean> fetchAllSlot(SlotBean slotBean, int userId) throws DataException
    {
        try
        {
            Optional<UserModel> userModelOptional = userRepository.findById(userId);
            ValidationHelper.checkUserById(userModelOptional);

            List<SlotBean> slotBeanList = new ArrayList<>();
            List<SlotModel> slotModelList = new ArrayList<>();

            if(NullEmptyUtils.isNullorEmpty(slotBean.getParkingAreaId()))
            {
                throw new DataException(Constants.EXCEPTION,messageBundle.getMessage(Constants.EMPTY_FIELD),HttpStatus.BAD_REQUEST);
            }

            slotModelList = slotRepository.findByParkingAreaId(slotBean.getParkingAreaId());

            for(SlotModel slotModel : slotModelList)
            {
                slotBeanList.add(setSlotBean(slotModel));
            }

            return slotBeanList;
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
     * private method to set slotBean
     *
     * @param slotModel
     * @return
     */

    private SlotBean setSlotBean(SlotModel slotModel)
    {
        SlotBean slotBean = new SlotBean();

        slotBean.setSlotId(slotModel.getSlotId());
        slotBean.setParkingAreaId(slotModel.getParkingAreaId());
        slotBean.setSlotLat(slotModel.getSlotLat());
        slotBean.setSlotLong(slotModel.getSlotLong());

        return slotBean;
    }
}
