package com.spd.exception;

public class AlreadyHaveUserEmail extends RuntimeException {

    public AlreadyHaveUserEmail(String s) {
        super(s);
    }

}
