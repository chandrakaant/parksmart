package com.highpeak.parksmart.controller;

import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.LoginBean;
import com.highpeak.parksmart.pojo.LoginResponseBean;
import com.highpeak.parksmart.util.RestRequestHeader;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/rest/users")
public class UserController extends AbstractRestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @PostMapping( "/login" ) @ApiOperation( value = "Login", code = 200, produces = "application/json", notes = "Rest API for Login of User" ) @ApiResponses( value = {
            @ApiResponse( code = 200, message = RestRequestHeader.SUCCESS_MESSAGE, response = LoginResponseBean.class ),
            @ApiResponse( code = 401, message = RestRequestHeader.UNAUTHORIZED, response = DataException.class ) } )
    public void loginToShowInSWagger(@ApiParam( "User" ) @RequestBody LoginBean loginBean )
    {
        throw new IllegalStateException(
                "This method shouldn't be called. It's implemented by Spring Security filters.");
    }

}
