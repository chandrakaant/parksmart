package com.highpeak.parksmart.controller;

import com.highpeak.parksmart.datastore.repository.ParkingRepository;
import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.ParkingBean;
import com.highpeak.parksmart.service.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> startParking(@RequestBody ParkingBean parkingBean)
    {
        try
        {
            return buildResponse(parkingService.startParking(parkingBean));
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
    public ResponseEntity<?> stopParking(@RequestBody ParkingBean parkingBean)
    {
        try
        {
            return buildResponse(parkingService.stopParking(parkingBean));
        }
        catch(DataException e)
        {
            return buildError(e);
        }
    }

    /**
     * Service to find car
     *
     * @param parkingBean
     * @return
     */

    @PostMapping("/findCar")
    public ResponseEntity<?> findCar(@RequestBody ParkingBean parkingBean)
    {
        try
        {
            return buildResponse(parkingService.findCar(parkingBean));
        }
        catch(DataException e)
        {
            return buildError(e);
        }
    }
}
