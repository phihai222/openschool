package com.openschool.identity.exception;

public class RootUserAlreadyExistsException extends RuntimeException {
    public RootUserAlreadyExistsException() {
        super("Root user already exists");
    }
}