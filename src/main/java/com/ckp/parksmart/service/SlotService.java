package com.ckp.parksmart.service;

import com.ckp.parksmart.exception.DataException;
import com.ckp.parksmart.pojo.SlotBean;

import java.util.List;

public interface SlotService
{
    /**
     * Service to register slot details
     *
     * @param slotBean
     * @return
     */

    SlotBean registerSlot(SlotBean slotBean, int userId) throws DataException;

    /**
     * Service to fetch a slot by id
     *
     * @param slotBean
     * @return
     */

    SlotBean fetchSlotById(SlotBean slotBean, int userId) throws DataException;

    /**
     * Service to fetch all slots in a parking area
     *
     * @param slotBean
     * @return
     */

    List<SlotBean> fetchAllSlot(SlotBean slotBean, int userId) throws DataException;
}
