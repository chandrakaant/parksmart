package com.ckp.parksmart.controller;

import com.ckp.parksmart.exception.DataException;
import com.ckp.parksmart.pojo.ParkingAreaBean;
import com.ckp.parksmart.service.ParkingAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/parkingArea")
public class ParkingAreaController extends AbstractRestService
{
    private static Logger LOGGER = LoggerFactory.getLogger(ParkingAreaController.class);

    @Autowired
    ParkingAreaService parkingAreaService;

    /**
     * service to store parking area details
     *
     * @param parkingAreaBean
     * @return
     * @throws DataException
     */

    @PostMapping("/registerParkingArea")
    public ResponseEntity<?> registerParkingArea(@RequestBody ParkingAreaBean parkingAreaBean, @RequestHeader("Authorization") String authorization)
    {
        try
        {
            return buildResponse(parkingAreaService.registerParkingArea(parkingAreaBean, Integer.parseInt(getLoggedInUser().getPrincipal().toString())));
        }
        catch(DataException e)
        {
            return buildError(e);
        }
    }

    /**
     * service to fetch parking area by id
     *
     * @param parkingAreaBean
     * @return
     * @throws DataException
     */

    @PostMapping("/fetchParkingArea")
    public ResponseEntity<?> fetchParkingArea(@RequestBody ParkingAreaBean parkingAreaBean, @RequestHeader("Authorization") String authorization)
    {
        try
        {
            return buildResponse(parkingAreaService.fetchParkingArea(parkingAreaBean, Integer.parseInt(getLoggedInUser().getPrincipal().toString())));
        }
        catch(DataException e)
        {
            return buildError(e);
        }
    }

    /**
     * service to fetch all
     *
     * @return
     * @throws DataException
     */

    @GetMapping("/fetchAllParkingArea")
    public ResponseEntity<?> fetchAllParkingArea(@RequestHeader("Authorization") String authorization)
    {
        try
        {
            return buildResponse(parkingAreaService.fetchAllParkingArea(Integer.parseInt(getLoggedInUser().getPrincipal().toString())));
        }
        catch(DataException e)
        {
            return buildError(e);
        }
    }
}
