package com.intigral.weatherapi.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message, Throwable e) {

        super(message, e);
    }
}
