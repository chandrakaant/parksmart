package com.ckp.parksmart.service;

import com.ckp.parksmart.exception.DataException;
import com.ckp.parksmart.pojo.LoginBean;
import com.ckp.parksmart.pojo.UserBean;

public interface UserService {

    /**
     * service td add or  update user
     * @param userBean user bean
     * @return user bean
     * @throws DataException data exception
     */
    UserBean registerUser(UserBean userBean) throws DataException;

    /**
     * service td add or  update user
     * @param userBean user bean
     * @return user bean
     * @throws DataException data exception
     */
    UserBean updateUser(UserBean userBean, int userId) throws DataException;


    /**
     * service to send forgot password mail
     *
     * @param userBean user bean
     */
    String forgotPassword(UserBean userBean) throws DataException;

    /**
     * service to compare and set password
     *
     * @param loginBean user bean
     * @throws DataException data exception
     */
    String setNewPassword(LoginBean loginBean, int userId) throws DataException;

    /**
     * service to delete user
     *
     * @param userBean user bean
     * @param userId id of user deleting user
     * @throws DataException data exception
     */
    String deleteUser(UserBean userBean, int userId) throws DataException;

    UserBean fetchUser(int userId) throws DataException;
}
