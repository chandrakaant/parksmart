package com.highpeak.parksmart.controller;

import com.highpeak.parksmart.datastore.repository.ParkingRepository;
import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.ParkingBean;
import com.highpeak.parksmart.service.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/parking")
public class ParkingController extends AbstractRestService
{
    private static Logger LOGGER = LoggerFactory.getLogger(ParkingController.class);

    @Autowired
    ParkingRepository parkingRepository;

    @Autowired
    ParkingService parkingService;

    /**
     * Service to start parking
     *
     * @param parkingBean
     * @return
     */

    @PostMapping("/startParking")
    public ResponseEntity<?> startParking(@RequestBody ParkingBean parkingBean, @RequestHeader("Authorization") String authorization)
    {
        try
        {
            return buildResponse(parkingService.startParking(parkingBean, Integer.parseInt(getLoggedInUser().getPrincipal().toString())));
        }
        catch(DataException e)
        {
            return buildError(e);
        }
    }

    /**
     * Service to stop parking
     *
     * @param parkingBean
     * @return
     */

    @PostMapping("/stopParking")
    public ResponseEntity<?> stopParking(@RequestBody ParkingBean parkingBean, @RequestHeader("Authorization") String authorization)
    {
        try
        {
            return buildResponse(parkingService.stopParking(parkingBean, Integer.parseInt(getLoggedInUser().getPrincipal().toString())));
        }
        catch(DataException e)
        {
            return buildError(e);
        }
    }

    /**
     * Service to stop parking
     *
     * @param parkingBean
     * @return
     */

    @PostMapping("/findCar")
    public ResponseEntity<?> findCar(@RequestBody ParkingBean parkingBean, @RequestHeader("Authorization") String authorization)
    {
        try
        {
            return buildResponse(parkingService.findCar(parkingBean, Integer.parseInt(getLoggedInUser().getPrincipal().toString())));
        }
        catch(DataException e)
        {
            return buildError(e);
        }
    }
}
