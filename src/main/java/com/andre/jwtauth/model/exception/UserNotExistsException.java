package com.andre.jwtauth.model.exception;

public class UserNotExistsException extends CustomAuthException{

    public UserNotExistsException(Integer httpCode, String responseErrorMessage) {
        super(httpCode, responseErrorMessage);
    }
}
