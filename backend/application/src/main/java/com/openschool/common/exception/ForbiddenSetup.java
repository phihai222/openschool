package com.openschool.common.exception;

public class ForbiddenSetup extends RuntimeException{
    public ForbiddenSetup(String message) {
        super(message);
    }
}
