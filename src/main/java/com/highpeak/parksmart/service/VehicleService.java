package com.highpeak.parksmart.service;

import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.VehicleBean;


public interface VehicleService {

    VehicleBean addVehicle(VehicleBean vehicleBean, int userId) throws DataException;

    VehicleBean updateVehicle(VehicleBean vehicleBean, int userId) throws DataException;

    void deleteVehicle(VehicleBean vehicleBean, int userId) throws DataException;

    VehicleBean fetchVehicle(VehicleBean vehicleBean, int userId) throws DataException;

    VehicleBean fetchVehicleByUserId(VehicleBean vehicleBean) throws DataException;
}
