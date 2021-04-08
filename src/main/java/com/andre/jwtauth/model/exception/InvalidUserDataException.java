package com.andre.jwtauth.model.exception;

public class InvalidUserDataException extends CustomAuthException{

    public InvalidUserDataException(Integer httpCode, String responseErrorMessage) {
        super(httpCode, responseErrorMessage);
    }
}
