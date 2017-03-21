package com.spd.exception;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new ValidationException(message);
        }
    }

}
