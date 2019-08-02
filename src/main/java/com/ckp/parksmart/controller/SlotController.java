package com.ckp.parksmart.controller;

import com.ckp.parksmart.datastore.repository.SlotRepository;
import com.ckp.parksmart.exception.DataException;
import com.ckp.parksmart.pojo.SlotBean;
import com.ckp.parksmart.service.SlotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> registerSlot(@RequestBody SlotBean slotBean, @RequestHeader("Authorization") String authorization)
    {
        try
        {
            return buildResponse(slotService.registerSlot(slotBean, Integer.parseInt(getLoggedInUser().getPrincipal().toString())));
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
    public ResponseEntity<?> fetchSlotById(@RequestBody SlotBean slotBean, @RequestHeader("Authorization") String authorization)
    {
        try
        {
            return buildResponse(slotService.fetchSlotById(slotBean, Integer.parseInt(getLoggedInUser().getPrincipal().toString())));
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
    public ResponseEntity<?> fetchAllSlot(@RequestBody SlotBean slotBean, @RequestHeader("Authorization") String authorization)
    {
        try
        {
            return buildResponse(slotService.fetchAllSlot(slotBean, Integer.parseInt(getLoggedInUser().getPrincipal().toString())));
        }
        catch(DataException e)
        {
            return buildError(e);
        }
    }
}
