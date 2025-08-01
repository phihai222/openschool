package com.openschool.systemsetup.exeption;

public class ForbiddenSetup extends RuntimeException{
    public ForbiddenSetup(String message) {
        super(message);
    }
}
