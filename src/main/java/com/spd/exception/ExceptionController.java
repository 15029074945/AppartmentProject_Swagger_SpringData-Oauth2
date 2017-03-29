package com.spd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@Controller
public class ExceptionController {

    @ExceptionHandler(AnnouncementException.class)
    public ResponseEntity<String> announcementHandle(AnnouncementException e) {
        return new ResponseEntity<>("Not Announcement: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
