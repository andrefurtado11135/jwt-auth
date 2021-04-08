package com.andre.jwtauth.model.exception;

public class InvalidLoginException extends CustomAuthException{

    public InvalidLoginException(Integer httpCode, String responseErrorMessage) {
        super(httpCode, responseErrorMessage);
    }
}
