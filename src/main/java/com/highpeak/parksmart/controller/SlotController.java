package com.highpeak.parksmart.controller;

import com.highpeak.parksmart.datastore.repository.SlotRepository;
import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.ParkingAreaBean;
import com.highpeak.parksmart.pojo.SlotBean;
import com.highpeak.parksmart.service.ParkingAreaService;
import com.highpeak.parksmart.service.SlotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/slot")
public class SlotController extends AbstractRestService
{
    private static Logger LOGGER = LoggerFactory.getLogger(SlotController.class);

    @Autowired
    SlotRepository slotRepository;

    @Autowired
    SlotService slotService;

    /**
     * Service to register slot details
     *
     * @param slotBean
     * @return
     */

    @PostMapping("/registerSlot")
    public ResponseEntity<?> registerSlot(@RequestBody SlotBean slotBean)
    {
        try
        {
            return buildResponse(slotService.registerSlot(slotBean));
        }
        catch(DataException e)
        {
            return buildError(e);
        }
    }

    /**
     * Service to fetch a slot by id
     *
     * @param slotBean
     * @return
     */

    @PostMapping("/fetchSlotById")
    public ResponseEntity<?> fetchSlotById(@RequestBody SlotBean slotBean)
    {
        try
        {
            return buildResponse(slotService.fetchSlotById(slotBean));
        }
        catch(DataException e)
        {
            return buildError(e);
        }
    }

    /**
     * Service to fetch all slots in a parking area
     *
     * @param slotBean
     * @return
     */

    @PostMapping("/fetchAllSlot")
    public ResponseEntity<?> fetchAllSlot(@RequestBody SlotBean slotBean)
    {
        try
        {
            return buildResponse(slotService.fetchAllSlot(slotBean));
        }
        catch(DataException e)
        {
            return buildError(e);
        }
    }
}
