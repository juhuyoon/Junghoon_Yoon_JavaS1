package com.trilogyed.tasker.Exceptions;

//Run Time Exception Handler for Controllers
public class ControllerNotFoundException extends RuntimeException {
    public ControllerNotFoundException(String message) {
        super(message);
    }
}
