package com.highpeak.parksmart.controller;


import com.highpeak.parksmart.controller.response.UIErrorMessage;
import com.highpeak.parksmart.controller.response.UIResponse;
import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.UserAccessDetailsToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

public class AbstractRestService {

    /**
     * Get Logged In User
     *
     * @return
     */
    protected UserAccessDetailsToken getLoggedInUser() {
        UserAccessDetailsToken userAccessDetailsToken = new UserAccessDetailsToken(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                SecurityContextHolder.getContext().getAuthentication().getCredentials(),
                SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        return userAccessDetailsToken;
    }

    /**
     * Method to build success message response
     *
     * @param msg
     * @return
     */
    protected ResponseEntity<UIResponse<String>> buildSuccessMessage(final String msg) {
        final UIResponse<String> message = new UIResponse<String>();
        message.setEntity(".success");
        message.setMessage(msg);
        message.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<UIResponse<String>>(message, HttpStatus.OK);
    }

    /**
     * Method to build success message response
     *
     * @param t
     * @return
     */
    protected <T> ResponseEntity<UIResponse<T>> buildResponse(final T t) {
        final UIResponse<T> uiResponse = new UIResponse<T>(t);
        uiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<UIResponse<T>>(uiResponse, HttpStatus.OK);
    }

    /**
     * Method to build error response for data exception
     *
     * @param e
     * @return
     */
    @SuppressWarnings("rawtypes")
    public ResponseEntity<UIErrorMessage> buildError(final DataException e) {
        final UIErrorMessage message = new UIErrorMessage();
        message.setMessageCode(e.getErrorCode());
        message.setMessage(e.getErrorMessage());
        if (e.getHttpStatus().equals(HttpStatus.BAD_REQUEST)) {
            message.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<UIErrorMessage>(message, HttpStatus.BAD_REQUEST);
        }
        if (e.getHttpStatus().equals(HttpStatus.FORBIDDEN)) {
            message.setStatus(HttpStatus.FORBIDDEN.value());
            return new ResponseEntity<UIErrorMessage>(message, HttpStatus.FORBIDDEN);
        }
        if (e.getHttpStatus().equals(HttpStatus.NOT_FOUND)) {
            message.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<UIErrorMessage>(message, HttpStatus.NOT_FOUND);
        }
        if (e.getHttpStatus().equals(HttpStatus.CONFLICT)) {
            message.setStatus(HttpStatus.CONFLICT.value());
            return new ResponseEntity<UIErrorMessage>(message, HttpStatus.CONFLICT);
        }
        message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<UIErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Token parsing .
     *
     * @param request
     * @return
     */
    public String tokenParsing(HttpServletRequest request) {
        return request.getUserPrincipal().getName().split("#")[0];
    }

}
