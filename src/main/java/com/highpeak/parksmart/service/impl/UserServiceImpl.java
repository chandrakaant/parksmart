package com.highpeak.parksmart.service.impl;


import com.highpeak.parksmart.datastore.model.RoleEntity;
import com.highpeak.parksmart.datastore.model.UserModel;
import com.highpeak.parksmart.datastore.model.UserRoleModel;
import com.highpeak.parksmart.datastore.repository.RoleRepository;
import com.highpeak.parksmart.datastore.repository.UserRepository;
import com.highpeak.parksmart.datastore.repository.UserRoleRepository;
import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.UserBean;
import com.highpeak.parksmart.service.UserService;
import com.highpeak.parksmart.util.*;
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

    /**
     * service td add or  update user
     * @param userBean user bean
     * @param userId user id of user creating or updating user
     * @return user bean
     * @throws DataException data exception
     */
    @Override
    public UserBean addOrUpdateUser(UserBean userBean, int userId) throws DataException
    {
        LOGGER.info("add or update user");
        try
        {
            Optional<UserModel> optionalUserModel = userRepository.findByIdAndIsActiveTrue(userId);
            ValidationHelper.checkUserById(optionalUserModel);

            if(NullEmptyUtils.isNull(userBean.getUserId()))
                ValidationHelper.validateSaveUserProfile(userBean);

            UserModel userModel = new UserModel();
            Optional<UserModel> userModelOptional = null;
            String email = userBean.getEmail();


            if (!NullEmptyUtils.isNullorEmpty(userBean.getUserId()))
            {
                userModelOptional = userRepository.findByIdAndIsActiveTrue(userBean.getUserId());
                ValidationHelper.checkUserById(userModelOptional);
            }

            if (!NullEmptyUtils.isNullorEmpty(email))
            {
                userModelOptional = userRepository.findByEmailAndIsActiveTrue(email);
                ValidationHelper.checkByEmailId(userModelOptional);
            }

            if(!NullEmptyUtils.isNull(userModelOptional) && userModelOptional.isPresent())
                userModel = userModelOptional.get();


            userModel = userRepository.save(mapUserBeanToUserModel(userBean, userModel, optionalUserModel.get().getId()));


            if (!NullEmptyUtils.isNullorEmpty(userBean.getRole()))
                setUserRole(userBean, userModel);

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
     * private service to set user role
     * @param userBean
     * @param userModel
     */
    private void setUserRole(UserBean userBean, UserModel userModel)
    {
        LOGGER.info("Setting user role");

        Optional<RoleEntity> optionalRoleEntity = roleRepository.findByRoleId(userBean.getRole());
        RoleEntity roleEntity = optionalRoleEntity.get();

        UserRoleModel userRoleModel = NullEmptyUtils.isNull(userRoleRepository.findRoleIdByUserId(userModel.getId())) ?
                new UserRoleModel() : userRoleRepository.findRoleIdByUserId(userModel.getId());


        userRoleModel.setUserId(userModel);
        userRoleModel.setRoleId(roleEntity);
        userRoleRepository.save(userRoleModel);
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
        user.setRole(getRoleByUser(userModel).getRoleId());
        user.setActive(userModel.isActive());
        return user;
    }

    /**
     * method to get role entity from database
     *
     * @param userModel user model
     * @return role entity
     */
    private RoleEntity getRoleByUser(UserModel userModel)
    {
        UserRoleModel roleId = userRoleRepository.findRoleIdByUserId(userModel.getId());
        Optional<RoleEntity> roleEntity = roleRepository.findByRoleId(roleId.getRoleId().getRoleId());
        return roleEntity.get();
    }

    /**
     * maps user pojo to user model
     *
     * @param userBean user pojo
     * @param actingUserId id of user taking action of create or update
     * @return UserModel
     */
    private UserModel mapUserBeanToUserModel(UserBean userBean, UserModel userModel, int actingUserId)
    {
        if (NullEmptyUtils.isNullorEmpty(userBean.getUserId()) && !NullEmptyUtils.isNullorEmpty(userBean.getEmail()))
        {
            userModel.setEmail(userBean.getEmail());
            userModel.setUserName(userBean.getEmail().substring(0, userBean.getEmail().indexOf('@')));
        }
        else if (!NullEmptyUtils.isNullorEmpty(userBean.getEmail()))
        {
            userModel.setEmail(userBean.getEmail());
        }

        if(!NullEmptyUtils.isNullorEmpty(userBean.getName()))
            userModel.setUserName(userBean.getName());

        if (NullEmptyUtils.isNullorEmpty(userBean.getUserId()))
        {
            userModel.setCreatedDate(new Timestamp(DateUtil.convertToUTC(System.currentTimeMillis())));
            userModel.setActive(false);
        }

        if (!NullEmptyUtils.isNullorEmpty(userBean.getUserId()))
        userModel.setModifiedDate(new Timestamp(DateUtil.convertToUTC(System.currentTimeMillis())));
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
            //resetTokenGeneration.sendForgotPasswordMail(userModel);
        }
        catch (DataException e)
        {
            throw e;

        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage(Constants.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return Constants.PASSWORD_RESET_URL_HAS_BEEN_SENT;

    }

    /**
     * service to compare and set password
     *
     * @param token    unique token
     * @param userBean user bean
     * @throws DataException data exception
     */
    @Override
    public String setNewPassword(String token, UserBean userBean) throws DataException
    {
        LOGGER.info("Set password service impl is called - main server");
        try
        {
            Optional<UserModel> userModelOptional = userRepository.findById(userBean.getUserId());
            ValidationHelper.checkUserById(userModelOptional);
            UserModel userModel = userModelOptional.get();

            if (!(new Timestamp(System.currentTimeMillis())).before(userModel.getTokenExpiryDate()))
                throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage(Constants.TOKEN_EXPIRED),
                        HttpStatus.BAD_REQUEST);

            if (!NullEmptyUtils.isNullorEmpty(userBean.getUserId()))
            {
                if (userModel.getId() != userBean.getUserId())
                    throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage(Constants.INVALID_USER_DETAILS)
                            , HttpStatus.BAD_REQUEST);
            }

            if (!NullEmptyUtils.isNullorEmpty(userBean.getPassword()))
                userModel.setPassword(bCryptPasswordEncoder.encode(userBean.getPassword()));

            userModel.setActive(true);
            userRepository.save(userModel);
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

            Optional<UserModel> userModelOptional = userRepository.findByIdAndIsActiveTrue(userBean.getUserId());
            ValidationHelper.checkUserById(userModelOptional);
            UserModel userModel = userModelOptional.get();
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

}
