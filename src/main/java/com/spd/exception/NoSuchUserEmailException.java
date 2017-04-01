package com.spd.exception;

public class NoSuchUserEmailException extends RuntimeException {

    public NoSuchUserEmailException(String s) {
        super(s);
    }

}
