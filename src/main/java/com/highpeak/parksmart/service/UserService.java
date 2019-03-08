package com.highpeak.parksmart.service;


import com.highpeak.parksmart.datastore.model.UserModel;
import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.UserBean;

public interface UserService {

    /**
     * service td add or  update user
     * @param userBean user bean
     * @param userId user id of user creating or updating user
     * @return user bean
     * @throws DataException data exception
     */
    UserBean addOrUpdateUser(UserBean userBean, int userId) throws DataException;

    /**
     * service to send forgot password mail
     *
     * @param userBean user bean
     */
    String forgotPassword(UserBean userBean) throws DataException;

    /**
     * service to compare and set password
     *
     * @param token    unique token
     * @param userBean user bean
     * @throws DataException data exception
     */
    String setNewPassword(String token, UserBean userBean) throws DataException;

    /**
     * service to delete user
     *
     * @param userBean user bean
     * @param userId id of user deleting user
     * @throws DataException data exception
     */
    String deleteUser(UserBean userBean, int userId) throws DataException;
}
