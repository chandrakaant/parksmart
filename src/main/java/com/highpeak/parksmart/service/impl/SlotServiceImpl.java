package com.highpeak.parksmart.service.impl;

import com.highpeak.parksmart.datastore.model.SlotModel;
import com.highpeak.parksmart.datastore.repository.SlotRepository;
import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.SlotBean;
import com.highpeak.parksmart.service.SlotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlotServiceImpl implements SlotService
{
    private static Logger LOGGER = LoggerFactory.getLogger(SlotServiceImpl.class);

    @Autowired
    SlotRepository slotRepository;

    /**
     * Service to register slot
     *
     * @param slotBean
     * @return
     * @throws DataException
     */

    @Override
    public SlotBean registerSlot(SlotBean slotBean) throws DataException
    {
        SlotModel slotModel = new SlotModel();

        slotModel.setParkingAreaId(slotBean.getParkingAreaId());
        slotModel.setSlotLocation(slotBean.getSlotLocation());

        return setSlotBean(slotRepository.save(slotModel));
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
        slotBean.setSlotLocation(slotModel.getSlotLocation());

        return slotBean;
    }
}

