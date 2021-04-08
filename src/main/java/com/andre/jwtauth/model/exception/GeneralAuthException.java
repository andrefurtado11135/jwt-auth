package com.andre.jwtauth.model.exception;

public class GeneralAuthException extends CustomAuthException{

    public GeneralAuthException(Integer httpCode, String responseErrorMessage) {
        super(httpCode, responseErrorMessage);
    }
}
