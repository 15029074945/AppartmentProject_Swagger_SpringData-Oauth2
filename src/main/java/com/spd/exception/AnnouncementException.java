package com.spd.exception;

public class AnnouncementException extends RuntimeException {

    public AnnouncementException() {
        super();
    }

    public AnnouncementException(String s) {
        super(s);
    }

    public AnnouncementException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AnnouncementException(Throwable throwable) {
        super(throwable);
    }

}
