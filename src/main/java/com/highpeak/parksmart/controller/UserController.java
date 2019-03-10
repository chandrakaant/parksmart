package com.highpeak.parksmart.controller;

import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.LoginBean;
import com.highpeak.parksmart.pojo.LoginResponseBean;
import com.highpeak.parksmart.pojo.UserBean;
import com.highpeak.parksmart.service.UserService;
import com.highpeak.parksmart.util.RestRequestHeader;
import com.highpeak.parksmart.util.ValidationHelper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Validated
@RestController
@RequestMapping("/rest/users")
public class UserController extends AbstractRestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping( "/login" ) @ApiOperation( value = "Login", code = 200, produces = "application/json", notes = "Rest API for Login of User" ) @ApiResponses( value = {
            @ApiResponse( code = 200, message = RestRequestHeader.SUCCESS_MESSAGE, response = LoginResponseBean.class ),
            @ApiResponse( code = 401, message = RestRequestHeader.UNAUTHORIZED, response = DataException.class ) } )
    public void loginToShowInSWagger(@ApiParam( "User" ) @RequestBody LoginBean loginBean )
    {
        throw new IllegalStateException(
                "This method shouldn't be called. It's implemented by Spring Security filters.");
    }

    @PostMapping("/register")@ApiOperation( value = "register", code = 200, produces = "application/json", notes = "Rest API for Login of User" ) @ApiResponses( value = {
    @ApiResponse( code = 200, message = RestRequestHeader.SUCCESS_MESSAGE, response = LoginResponseBean.class ),
    @ApiResponse( code = 401, message = RestRequestHeader.UNAUTHORIZED, response = DataException.class ) } )
    public ResponseEntity<?> registerUser(@RequestBody UserBean userBean, HttpServletRequest request){
        try
        {
            LOGGER.info("Register user service is called");
            ValidationHelper.checkEmptyEmailField(userBean);
            return buildResponse(userService.registerUser(userBean));
        }
        catch (final DataException e)
        {
            return buildError(e);
        }
    }

    /**
     * controller to update user
     *
     * @param userBean user bean
     * @param authorization  token
     * @return object
     */
    @PostMapping("/updateUser")
    @ApiOperation( value = "updateUser", code = 200, produces = "application/json", notes = "Rest API to update user" ) @ApiResponses( value = {
            @ApiResponse( code = 200, message = RestRequestHeader.VALUE, response = ResponseEntity.class ),
            @ApiResponse( code = 400, message = RestRequestHeader.BAD_REQUEST_ERROR, response = DataException.class ) } )
    public ResponseEntity<?> updateUser(@RequestBody UserBean userBean, @RequestHeader("Authorization") String authorization)
    {
        try
        {
            LOGGER.info("update user service is called");
            ValidationHelper.checkEmptyIdField(userBean);
            return buildResponse(userService.updateUser(userBean, Integer.parseInt(getLoggedInUser().getPrincipal().toString())));
        }
        catch (final DataException e)
        {
            return buildError(e);
        }
    }

    /**
     * controller to send link to user for reset password
     *
     * @param userBean user pojo
     * @return object
     */
    @PostMapping("/forgotPassword")
    @ApiOperation( value = "forgotPassword", code = 200, produces = "application/json", notes = "Rest API to send forgot password mail" ) @ApiResponses( value = {
            @ApiResponse( code = 200, message = RestRequestHeader.SUCCESS_MESSAGE, response = ResponseEntity.class ),
            @ApiResponse( code = 400, message = RestRequestHeader.BAD_REQUEST_ERROR, response = DataException.class ) } )
    public ResponseEntity<?> forgotPassword(@RequestBody UserBean userBean)
    {
        try
        {
            LOGGER.info("Forgot password service is called");
            ValidationHelper.checkEmptyEmailField(userBean);
            return buildResponse(userService.forgotPassword(userBean));
        }
        catch (final DataException e)
        {
            return buildError(e);
        }
    }

    /**
     * controller to set password
     *
     * @param loginBean bean
     * @return object
     */
    @PostMapping("/setPassword")
    @ApiOperation( value = "set password", code = 200, produces = "application/json", notes = "Rest API to set new password" ) @ApiResponses( value = {
            @ApiResponse( code = 200, message = RestRequestHeader.SUCCESS_MESSAGE, response = ResponseEntity.class ),
            @ApiResponse( code = 400, message = RestRequestHeader.BAD_REQUEST_ERROR, response = DataException.class ) } )
    public ResponseEntity<?> setNewPassword(@RequestBody LoginBean loginBean)
    {
        try
        {
            LOGGER.info("Set password service is called");
            return buildResponse(userService.setNewPassword(loginBean,  Integer.parseInt(getLoggedInUser().getPrincipal().toString())));
        }
        catch (final DataException e)
        {
            return buildError(e);
        }
    }

    /**
     * controller to delete user using id
     *
     * @param userBean user pojo
     * @param authorization  token
     * @return object
     */
    @PostMapping("/delete")
    @ApiOperation( value = "delete", code = 200, produces = "application/json", notes = "Rest API to delete user" ) @ApiResponses( value = {
            @ApiResponse( code = 200, message = RestRequestHeader.SUCCESS_MESSAGE, response = ResponseEntity.class ),
            @ApiResponse( code = 400, message = RestRequestHeader.BAD_REQUEST_ERROR, response = DataException.class ) } )
    public ResponseEntity<?> deleteUser(@RequestBody UserBean userBean, @RequestHeader("Authorization") String authorization)
    {
        try
        {
            LOGGER.info("Delete user service is called");
            ValidationHelper.checkEmptyIdField(userBean);
            return buildResponse(userService.deleteUser(userBean, Integer.parseInt(getLoggedInUser().getPrincipal().toString())));
        }
        catch (final DataException e)
        {
            return buildError(e);
        }
    }

    /**
     * Controller for fetching user
     *
     * @param authorization           token
     * @return object
     */
    @PostMapping("/fetch")
    @ApiOperation( value = "fetch", code = 200, produces = "application/json", notes = "Rest API to fetch user" ) @ApiResponses( value = {
            @ApiResponse( code = 200, message = RestRequestHeader.LIST_CONTAINER, response = ResponseEntity.class ),
            @ApiResponse( code = 400, message = RestRequestHeader.BAD_REQUEST_ERROR, response = DataException.class ) } )
    public ResponseEntity<?> fetchUser(@RequestBody UserBean userBean, @RequestHeader("Authorization") String authorization)
    {
        try
        {
            LOGGER.info("Fetch user service is called");
            ValidationHelper.checkEmptyIdField(userBean);
            return buildResponse(userService.fetchUser(Integer.parseInt(getLoggedInUser().getPrincipal().toString())));
        }
        catch (final DataException e)
        {
            return buildError(e);
        }

    }



}
