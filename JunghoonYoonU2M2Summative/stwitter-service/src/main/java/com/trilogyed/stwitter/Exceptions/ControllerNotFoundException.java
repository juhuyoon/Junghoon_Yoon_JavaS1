package com.trilogyed.stwitter.Exceptions;


public class ControllerNotFoundException extends RuntimeException {
    public ControllerNotFoundException(String message) {
        super(message);
    }
}
