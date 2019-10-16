package com.company.JunghoonYoonGameStore.Exceptions;

public class ControllerNotFoundException extends RuntimeException {
    public ControllerNotFoundException() {
        super();
    }

    public ControllerNotFoundException(String message) {
        super(message);
    }
}
