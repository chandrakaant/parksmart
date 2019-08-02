package com.ckp.parksmart.exception;

import org.springframework.http.HttpStatus;

public class DataException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 8393688636580014922L;

    private String errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;

    /**
     * @param errorCode
     * @param errorMessage
     * @param httpStatus
     */
    public DataException(final String errorCode, final String errorMessage, final HttpStatus httpStatus) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public DataException() {

    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(final HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }


}
