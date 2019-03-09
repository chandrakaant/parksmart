package com.highpeak.parksmart.controller;

import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.VehicleBean;
import com.highpeak.parksmart.service.VehicleService;
import com.highpeak.parksmart.util.RestRequestHeader;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/vehicle")
public class VehicleController extends AbstractRestService{

    private static Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private VehicleService vehicleService;

    @PostMapping( "/add" ) @ApiOperation( value = "Add vehicle", code = 200, produces = "application/json", notes = "Rest API for Adding vehicle" )
            @ApiResponses( value = {
            @ApiResponse( code = 200, message = RestRequestHeader.SUCCESS_MESSAGE, response = VehicleBean.class ),
            @ApiResponse( code = 401, message = RestRequestHeader.UNAUTHORIZED, response = DataException.class ) } )
    public ResponseEntity<?> addVehicle(@RequestBody VehicleBean vehicleBean, @RequestHeader("Authorization") String authorization)
    {
        try
        {
            return buildResponse(vehicleService.addVehicle(vehicleBean, Integer.parseInt(getLoggedInUser().getPrincipal().toString())));
        }
        catch (final DataException e)
        {
            return buildError(e);
        }
    }

    @PostMapping( "/update" ) @ApiOperation( value = "Update vehicle", code = 200, produces = "application/json", notes = "Rest API Updating vehicle" )
    @ApiResponses( value = {
            @ApiResponse( code = 200, message = RestRequestHeader.SUCCESS_MESSAGE, response = VehicleBean.class ),
            @ApiResponse( code = 401, message = RestRequestHeader.UNAUTHORIZED, response = DataException.class ) } )
    public ResponseEntity<?> updateVehicle(@RequestBody VehicleBean vehicleBean, @RequestHeader("Authorization") String authorization)
    {
        try
        {
            return buildResponse(vehicleService.updateVehicle(vehicleBean, Integer.parseInt(getLoggedInUser().getPrincipal().toString())));
        }
        catch (final DataException e)
        {
            return buildError(e);
        }
    }

    @PostMapping( "/fetch" ) @ApiOperation( value = "fetch vehicle", code = 200, produces = "application/json", notes = "Rest API for fetching vehicle" )
    @ApiResponses( value = {
            @ApiResponse( code = 200, message = RestRequestHeader.SUCCESS_MESSAGE, response = VehicleBean.class ),
            @ApiResponse( code = 401, message = RestRequestHeader.UNAUTHORIZED, response = DataException.class ) } )
    public ResponseEntity<?> fetchVehicle(@RequestBody VehicleBean vehicleBean, @RequestHeader("Authorization") String authorization)
    {
        try
        {
            return buildResponse(vehicleService.fetchVehicle(vehicleBean, Integer.parseInt(getLoggedInUser().getPrincipal().toString())));
        }
        catch (final DataException e)
        {
            return buildError(e);
        }
    }

    @PostMapping( "/delete" ) @ApiOperation( value = "delete vehicle", code = 200, produces = "application/json", notes = "Rest API for deleting vehicle" )
    @ApiResponses( value = {
            @ApiResponse( code = 200, message = RestRequestHeader.SUCCESS_MESSAGE, response = VehicleBean.class ),
            @ApiResponse( code = 401, message = RestRequestHeader.UNAUTHORIZED, response = DataException.class ) } )
    public ResponseEntity<?> deleteVehicle(@RequestBody VehicleBean vehicleBean, @RequestHeader("Authorization") String authorization)
    {
        try
        {
            vehicleService.deleteVehicle(vehicleBean, Integer.parseInt(getLoggedInUser().getPrincipal().toString()));
            return buildSuccessMessage("Vehicle Deleted Successfully");
        }
        catch (final DataException e)
        {
            return buildError(e);
        }
    }



}
