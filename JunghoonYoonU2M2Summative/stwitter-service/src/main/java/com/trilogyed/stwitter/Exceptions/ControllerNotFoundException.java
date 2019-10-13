package com.trilogyed.stwitter.Exceptions;


//Controller Not Found Exception Run Time Handler
public class ControllerNotFoundException extends RuntimeException {
    public ControllerNotFoundException(String message) {
        super(message);
    }
}
