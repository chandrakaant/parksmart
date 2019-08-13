package com.ckp.parksmart.service.impl;


import com.ckp.parksmart.datastore.model.RoleEntity;
import com.ckp.parksmart.datastore.model.UserModel;
import com.ckp.parksmart.datastore.model.UserRoleModel;
import com.ckp.parksmart.datastore.model.VehicleModel;
import com.ckp.parksmart.datastore.repository.RoleRepository;
import com.ckp.parksmart.datastore.repository.UserRepository;
import com.ckp.parksmart.datastore.repository.UserRoleRepository;
import com.ckp.parksmart.datastore.repository.VehicleRepository;
import com.ckp.parksmart.exception.DataException;
import com.ckp.parksmart.pojo.LoginBean;
import com.ckp.parksmart.pojo.UserBean;
import com.ckp.parksmart.pojo.VehicleBean;
import com.ckp.parksmart.service.CacheService;
import com.ckp.parksmart.service.UserService;
import com.ckp.parksmart.service.VehicleService;
import com.ckp.parksmart.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    MessageBundleResource messageBundleResource;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private OtpGeneration otpGeneration;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    /**
     * service td add or  update user
     * @param userBean user bean
     * @return user bean
     * @throws DataException data exception
     */
    @Override
    public UserBean registerUser(UserBean userBean) throws DataException
    {
        LOGGER.info("register user");
        try
        {
            Optional<UserModel> optionalUserModel = userRepository.findByEmailAndIsActiveTrue(userBean.getEmail());
            ValidationHelper.checkByEmailId(optionalUserModel);

            UserModel userModel = new UserModel();
            userModel = mapUserBeanToUserModel(userBean, userModel);

            if (userBean.getPassword().equals(userBean.getConfirmPassword()))
                userModel.setPassword(bCryptPasswordEncoder.encode(userBean.getPassword()));

            userModel = userRepository.save(userModel);

            setUserRole(userModel);
            return mapUserModelToUserBean(userModel);
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
     * private function to set user role
     * @param userModel user model
     */
    private void setUserRole(UserModel userModel)
    {
        LOGGER.info("Setting user role");

        Optional<RoleEntity> optionalRoleEntity = roleRepository.findByRoleName("DRIVER");
        RoleEntity roleEntity = optionalRoleEntity.get();

        UserRoleModel userRoleModel = NullEmptyUtils.isNull(userRoleRepository.findRoleIdByUserId(userModel.getId())) ?
                new UserRoleModel() : userRoleRepository.findRoleIdByUserId(userModel.getId());


        userRoleModel.setUserId(userModel);
        userRoleModel.setRoleId(roleEntity);
        userRoleRepository.save(userRoleModel);
    }


    /**
     * service to update user
     * @param userBean user bean
     * @param userId user id
     * @return user bean
     * @throws DataException exception
     */
    @Override
    public UserBean updateUser(UserBean userBean, int userId) throws DataException
    {
        try
        {
            Optional<UserModel> optionalUserModel = userRepository.findByIdAndIsActiveTrue(userId);
            ValidationHelper.checkUserById(optionalUserModel);

            UserModel userModel = optionalUserModel.get();


            userModel = userRepository.save(mapUserBeanToUserModel(userBean, userModel));

            VehicleBean vehicleBean = new VehicleBean();

            if (!NullEmptyUtils.isNullorEmpty(userBean.getNumber()))
                vehicleBean.setNumber(userBean.getNumber());
            if (!NullEmptyUtils.isNullorEmpty(userBean.getVehicleName()))
                vehicleBean.setNumber(userBean.getVehicleName());
            if (!NullEmptyUtils.isNullorEmpty(userBean.getManufacturerName()))
                vehicleBean.setManufacturerName(userBean.getManufacturerName());
            if (!NullEmptyUtils.isNullorEmpty(userBean.getLocation()))
                vehicleBean.setLocation(userBean.getVehicleLocation());

            VehicleBean bean = new VehicleBean();

            Optional<VehicleModel> vehicleModelOptional = vehicleRepository.findByUserIdAndIsActiveTrue(userModel.getId());

            if (!vehicleModelOptional.isPresent()){
                bean = vehicleService.addVehicle(vehicleBean, userModel.getId());
            }
            else {
                vehicleBean.setId(vehicleModelOptional.get().getId());
                bean = vehicleService.updateVehicle(vehicleBean, userModel.getId());
            }

            UserBean userBean1 = mapUserModelToUserBean(userModel);
            userBean1.setNumber(bean.getNumber());
            userBean1.setManufacturerName(bean.getManufacturerName());
            userBean1.setVehicleName(bean.getName());
            userBean1.setVehicleLocation(bean.getLocation());
            return userBean1;

        }
        catch (DataException e)
        {
            e.printStackTrace();
            throw e;

        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage(Constants.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * private function to create response if user id created
     *
     * @param userModel user model
     * @return user pojo
     */
    private UserBean mapUserModelToUserBean(UserModel userModel)
    {
        UserBean user = new UserBean();
        user.setUserId(userModel.getId());
        user.setEmail(userModel.getEmail());
        user.setName(userModel.getUserName());
        user.setPhone(userModel.getPhone());
        return user;
    }

    /**
     * maps user pojo to user model
     *
     * @param userBean user pojo
     * @return UserModel
     */
    private UserModel mapUserBeanToUserModel(UserBean userBean, UserModel userModel)
    {
        if (!NullEmptyUtils.isNullorEmpty(userBean.getEmail()))
            userModel.setEmail(userBean.getEmail());
        if (!NullEmptyUtils.isNullorEmpty(userBean.getPhone()))
            userModel.setPhone(userBean.getPhone());
        if (!NullEmptyUtils.isNullorEmpty(userBean.getName()))
            userModel.setUserName(userBean.getName());

        if (NullEmptyUtils.isNullorEmpty(userBean.getUserId()))
            userModel.setCreatedDate(new Timestamp(DateUtil.convertToUTC(System.currentTimeMillis())));

        userModel.setModifiedDate(new Timestamp(DateUtil.convertToUTC(System.currentTimeMillis())));
        userModel.setActive(true);

        return userModel;
    }

    /**
     * service to send forgot password mail
     *
     * @param userBean user pojo
     */
    @Override
    public String forgotPassword(UserBean userBean) throws DataException
    {
        LOGGER.info("forgot password service is called");
        try
        {
            Optional<UserModel> userModelOptional = userRepository.findByEmailAndIsActiveTrue(userBean.getEmail());
            if (!userModelOptional.isPresent())
                throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage(Constants.USER_DOES_NOT_EXIST),
                        HttpStatus.BAD_REQUEST);

            UserModel userModel = userModelOptional.get();
            otpGeneration.generateOTP(userModel.getEmail(), userModel.getId());
        }
        catch (DataException e)
        {
            e.printStackTrace();
            throw e;

        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage(Constants.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return Constants.PASSWORD_RESET_URL_HAS_BEEN_SENT;

    }

    /**
     * service to compare and set password
     *
     * @param loginBean user bean
     * @throws DataException data exception
     */
    @Override
    public String setNewPassword(LoginBean loginBean, int userId) throws DataException
    {
        LOGGER.info("Set password service impl is called - main server");
        try
        {
            Optional<UserModel> userModelOptional = userRepository.findById(userId);
            ValidationHelper.checkUserById(userModelOptional);
            UserModel userModel = userModelOptional.get();

            System.out.println(loginBean.getOtp());
            System.out.println(cacheService.gettingCache(userModel.getId()));
            System.out.println(userId);

            if (cacheService.gettingCache(userModel.getId()).equals("Otp expired"))
            {
                LOGGER.error("otp expired");
                throw new DataException(Constants.EXCEPTION,
                        messageBundleResource.getMessage("OTP_EXPIRED"), HttpStatus.BAD_REQUEST);
            }

            if (loginBean.getOtp().equalsIgnoreCase(cacheService.gettingCache(userModel.getId())))
            {
                if (!NullEmptyUtils.isNullorEmpty(loginBean.getPassword()))
                    userModel.setPassword(bCryptPasswordEncoder.encode(loginBean.getPassword()));

                userModel.setActive(true);
                userRepository.save(userModel);
            }
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

        return Constants.PASSWORD_HAS_BEEN_SET_SUCCESSFULLY;

    }

    /**
     * service to delete user
     *
     * @param userBean user bean
     * @param userId id of user deleting user
     * @throws DataException data exception
     */
    @Override
    public String deleteUser(UserBean userBean, int userId) throws DataException
    {
        LOGGER.info("Delete user service is called");
        try
        {
            Optional<UserModel> optionalUserModel = userRepository.findByIdAndIsActiveTrue(userId);
            ValidationHelper.checkUserById(optionalUserModel);

            UserModel userModel = optionalUserModel.get();
            userModel.setModifiedDate(new Timestamp(DateUtil.convertToUTC(System.currentTimeMillis())));
            userModel.setActive(false);
            userRepository.save(userModel);
            return Constants.USER_DELETED_SUCCESSFULLY;
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
     *
     * @param userId
     * @return
     * @throws DataException
     */
    @Override
    public UserBean fetchUser(int userId) throws DataException
    {
        Optional<UserModel> optionalUserModel = userRepository.findByIdAndIsActiveTrue(userId);
        ValidationHelper.checkUserById(optionalUserModel);

        UserModel userModel = optionalUserModel.get();

        VehicleModel bean = new VehicleModel();

        Optional<VehicleModel> vehicleModelOptional = vehicleRepository.findByUserIdAndIsActiveTrue(userModel.getId());

        UserBean userBean1 = mapUserModelToUserBean(userModel);

        if (vehicleModelOptional.isPresent())
        {
            bean = vehicleModelOptional.get();
            userBean1.setNumber(bean.getVehicleNumber());
            userBean1.setManufacturerName(bean.getManufacturerName());
            userBean1.setVehicleName(bean.getVehicleName());
            userBean1.setVehicleLocation(bean.getVehicleLocation());
        }

        return userBean1;
    }
}
