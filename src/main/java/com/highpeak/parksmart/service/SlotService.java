package com.highpeak.parksmart.service;

import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.SlotBean;

public interface SlotService
{
    SlotBean registerSlot(SlotBean slotBean) throws DataException;
}
