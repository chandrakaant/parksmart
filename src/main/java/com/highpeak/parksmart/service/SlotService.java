package com.highpeak.parksmart.service;

import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.SlotBean;

import java.util.List;

public interface SlotService
{
    /**
     * Service to register slot details
     *
     * @param slotBean
     * @return
     */

    SlotBean registerSlot(SlotBean slotBean) throws DataException;

    /**
     * Service to fetch a slot by id
     *
     * @param slotBean
     * @return
     */

    SlotBean fetchSlotById(SlotBean slotBean) throws DataException;

    /**
     * Service to fetch all slots in a parking area
     *
     * @param slotBean
     * @return
     */

    List<SlotBean> fetchAllSlot(SlotBean slotBean) throws DataException;
}
