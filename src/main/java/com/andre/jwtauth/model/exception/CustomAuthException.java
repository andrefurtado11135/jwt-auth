package com.andre.jwtauth.model.exception;

public abstract class CustomAuthException extends Throwable{

    private Integer httpCode;

    private String responseErrorMessage;

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getResponseErrorMessage() {
        return responseErrorMessage;
    }

    public void setResponseErrorMessage(String responseErrorMessage) {
        this.responseErrorMessage = responseErrorMessage;
    }

    public CustomAuthException(Integer httpCode, String responseErrorMessage) {
        this.httpCode = httpCode;
        this.responseErrorMessage = responseErrorMessage;
    }
}
