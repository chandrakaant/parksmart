package com.ckp.parksmart.service;

import com.ckp.parksmart.exception.DataException;
import com.ckp.parksmart.pojo.VehicleBean;


public interface VehicleService {

    VehicleBean addVehicle(VehicleBean vehicleBean, int userId) throws DataException;

    VehicleBean updateVehicle(VehicleBean vehicleBean, int userId) throws DataException;

    void deleteVehicle(VehicleBean vehicleBean, int userId) throws DataException;

    VehicleBean fetchVehicle(VehicleBean vehicleBean, int userId) throws DataException;

    VehicleBean fetchVehicleByUserId(VehicleBean vehicleBean) throws DataException;
}
