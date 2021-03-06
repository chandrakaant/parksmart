package com.ckp.parksmart.util;

import com.ckp.parksmart.datastore.model.UserModel;
import com.ckp.parksmart.exception.DataException;
import com.ckp.parksmart.pojo.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidationHelper {

    private static MessageBundleResource messageBundleResource;

    @Autowired
    public ValidationHelper( MessageBundleResource messageBundleResource )
    {
        ValidationHelper.messageBundleResource = messageBundleResource;
    }

    public static void validateSaveUserProfile( UserBean userBean ) throws DataException
    {
        try
        {
            if( NullEmptyUtils.isNull(userBean) )
            {
                throw new DataException("400", messageBundleResource.getMessage(Constants.INVALID_USER_DETAILS),
                        HttpStatus.BAD_REQUEST);
            }

            if( NullEmptyUtils.isNullorEmpty(userBean.getEmail().trim())
                    && !ValidateUserDetailsUtils.isInvalidEmail(userBean.getEmail()))
            {
                throw new DataException("400", messageBundleResource.getMessage(Constants.EMPTY_EMAIL_FIELD),
                        HttpStatus.BAD_REQUEST);
            }

            if( !NullEmptyUtils.isNullorEmpty(userBean.getPhone()) &&
                    !ValidateUserDetailsUtils.isInvalidPhoneNumber(String.valueOf(userBean.getPhone())))
            {

                throw new DataException("400", messageBundleResource.getMessage("EMPTY/INVALID PHONE NUMBER"),
                        HttpStatus.BAD_REQUEST);
            }

        }
        catch( DataException e )
        {
            throw e;
        }
    }

    public static void checkUserById( Optional<UserModel> userModelOptional ) throws DataException
    {
        if( !userModelOptional.isPresent() )
        {
            throw new DataException(Constants.EXCEPTION,
                    messageBundleResource.getMessage(Constants.USER_DOES_NOT_EXIST), HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkByEmailId( Optional<UserModel> userModelOptional ) throws DataException
    {
        if( userModelOptional.isPresent() )
            throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage(Constants.USER_EXISTS),
                    HttpStatus.BAD_REQUEST);
    }

    public static void checkEmptyEmailField( UserBean userBean ) throws DataException
    {
        if( NullEmptyUtils.isNullorEmpty(userBean.getEmail()) )
        {
            throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage(Constants.EMPTY_EMAIL_FIELD),
                    HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkEmptyIdField( UserBean userBean ) throws DataException
    {
        if( NullEmptyUtils.isNullorEmpty(userBean.getUserId()) )
        {
            throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage(Constants.EMPTY_ID_FIELD),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
