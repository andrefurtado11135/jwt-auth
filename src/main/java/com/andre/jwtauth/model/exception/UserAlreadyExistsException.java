package com.andre.jwtauth.model.exception;

public class UserAlreadyExistsException extends CustomAuthException{

    public UserAlreadyExistsException(Integer httpCode, String responseErrorMessage) {
        super(httpCode, responseErrorMessage);
    }
}
