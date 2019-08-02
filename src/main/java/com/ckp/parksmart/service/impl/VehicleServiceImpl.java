package com.ckp.parksmart.service.impl;

import com.ckp.parksmart.controller.VehicleController;
import com.ckp.parksmart.datastore.model.UserModel;
import com.ckp.parksmart.datastore.model.VehicleModel;
import com.ckp.parksmart.datastore.repository.UserRepository;
import com.ckp.parksmart.datastore.repository.VehicleRepository;
import com.ckp.parksmart.exception.DataException;
import com.ckp.parksmart.service.VehicleService;
import com.ckp.parksmart.pojo.VehicleBean;
import com.ckp.parksmart.util.Constants;
import com.ckp.parksmart.util.MessageBundleResource;
import com.ckp.parksmart.util.NullEmptyUtils;
import com.ckp.parksmart.util.ValidationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private static Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageBundleResource messageBundleResource;

    @Override
    public VehicleBean addVehicle(VehicleBean vehicleBean, int userId) throws DataException
    {
        LOGGER.info("Add vehicle");
        try
        {
            Optional<UserModel> optionalUserModel = userRepository.findByIdAndIsActiveTrue(userId);
            ValidationHelper.checkUserById(optionalUserModel);

            UserModel userModel = optionalUserModel.get();

            Optional<VehicleModel> vehicleModelOptional = vehicleRepository.findByVehicleNumberAndIsActiveTrue(vehicleBean.getNumber());
            if (vehicleModelOptional.isPresent())
                throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage("Vehicle already exist"),
                        HttpStatus.BAD_REQUEST);

            VehicleModel vehicleModel = mapVehicleBeanToModel(vehicleBean);
            vehicleModel.setUserId(userModel.getId());
            vehicleModel = vehicleRepository.save(vehicleModel);
            return mapVehicleModelToBean(vehicleModel);

        } catch (DataException e)
        {
            e.printStackTrace();
            throw e;

        } catch (Exception e)
        {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage(Constants.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private VehicleModel mapVehicleBeanToModel(VehicleBean vehicleBean)
    {
        VehicleModel vehicleModel = new VehicleModel();

        if (!NullEmptyUtils.isNullorEmpty(vehicleBean.getName()))
            vehicleModel.setVehicleName(vehicleBean.getName());

        if (!NullEmptyUtils.isNullorEmpty(vehicleBean.getNumber()))
            vehicleModel.setVehicleNumber(vehicleBean.getNumber());

        if (!NullEmptyUtils.isNullorEmpty(vehicleBean.getManufacturerName()))
            vehicleModel.setManufacturerName(vehicleBean.getManufacturerName());

        if (!NullEmptyUtils.isNullorEmpty(vehicleBean.getLocation()))
            vehicleModel.setVehicleLocation(vehicleBean.getLocation());

        vehicleModel.setActive(true);
        return vehicleModel;

    }

    private VehicleBean mapVehicleModelToBean(VehicleModel vehicleModel)
    {
        VehicleBean vehicleBean = new VehicleBean();
        vehicleBean.setId(vehicleModel.getId());
        vehicleBean.setLocation(vehicleModel.getVehicleLocation());
        vehicleBean.setName(vehicleModel.getVehicleName());
        vehicleBean.setNumber(vehicleModel.getVehicleNumber());
        vehicleBean.setManufacturerName(vehicleModel.getManufacturerName());
        return vehicleBean;
    }

    @Override
    public VehicleBean updateVehicle(VehicleBean vehicleBean, int userId) throws DataException
    {
        LOGGER.info("Update vehicle");
        try
        {
            Optional<UserModel> optionalUserModel = userRepository.findByIdAndIsActiveTrue(userId);
            ValidationHelper.checkUserById(optionalUserModel);

            Optional<VehicleModel> vehicleModelOptional = vehicleRepository.findByIdAndIsActiveTrue(vehicleBean.getId());
            if (!vehicleModelOptional.isPresent())
                throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage("Vehicle does not exist"),
                        HttpStatus.BAD_REQUEST);

            VehicleModel vehicleModel = vehicleModelOptional.get();
            vehicleModel.setActive(false);
            vehicleRepository.save(vehicleModel);
            return mapVehicleModelToBean(vehicleModel);

        }
        catch (DataException e)
        {
            throw e;

        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
            throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage(Constants.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteVehicle(VehicleBean vehicleBean, int userId) throws DataException
    {
        LOGGER.info("Delete vehicle");
        try
        {
            Optional<UserModel> optionalUserModel = userRepository.findByIdAndIsActiveTrue(userId);
            ValidationHelper.checkUserById(optionalUserModel);

            Optional<VehicleModel> vehicleModelOptional = vehicleRepository.findByIdAndIsActiveTrue(vehicleBean.getId());
            if (!vehicleModelOptional.isPresent())
                throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage("Vehicle does not exist"),
                        HttpStatus.BAD_REQUEST);

            VehicleModel vehicleModel = mapVehicleBeanToModel(vehicleBean);
            vehicleBean.setActive(false);
            vehicleRepository.save(vehicleModel);
        }
        catch (DataException e)
        {
            throw e;

        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
            throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage(Constants.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public VehicleBean fetchVehicle(VehicleBean vehicleBean, int userId) throws DataException
    {
        try
        {
            Optional<UserModel> optionalUserModel = userRepository.findByIdAndIsActiveTrue(userId);
            ValidationHelper.checkUserById(optionalUserModel);

            Optional<VehicleModel> vehicleModelOptional = vehicleRepository.findByIdAndIsActiveTrue(vehicleBean.getId());
            if (!vehicleModelOptional.isPresent())
                throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage("Vehicle does not exist"),
                        HttpStatus.BAD_REQUEST);

            return mapVehicleModelToBean(vehicleModelOptional.get());
        }
        catch (DataException e)
        {
            throw e;

        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
            throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage(Constants.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Service To Fetch Vehicle By User Id
     *
     * @param vehicleBean
     * @return
     */

    @Override
    public VehicleBean fetchVehicleByUserId(VehicleBean vehicleBean) throws DataException
    {
        if(NullEmptyUtils.isNullorEmpty(vehicleBean.getUserId()))
        {
            throw new DataException(Constants.EXCEPTION,messageBundleResource.getMessage(Constants.EMPTY_FIELD), HttpStatus.BAD_REQUEST);
        }

        Optional<VehicleModel> vehicleModel = vehicleRepository.findByUserIdAndIsActiveTrue(vehicleBean.getUserId());

        if(!vehicleModel.isPresent())
        {
            throw new DataException(Constants.EXCEPTION,messageBundleResource.getMessage(Constants.VEHICLE_DOESNT_EXIST), HttpStatus.BAD_REQUEST);
        }

        return mapVehicleModelToBean(vehicleModel.get());
    }
}
