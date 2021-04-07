package com.andre.jwtauth.model.exception;

public class CustomAuthException extends Exception{

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomAuthException(String message){
        super(message);
    }
}
