package com.spd.config;


import com.spd.bean.ErrorModelBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorModelBean> handleError(Exception ex) {

        ErrorModelBean errorModelBean =new ErrorModelBean(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity(errorModelBean, HttpStatus.INTERNAL_SERVER_ERROR);
    }
/*
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorModelBean> handleError(RuntimeException ex) {

        ErrorModelBean errorModelBean =new ErrorModelBean(ex.getMessage(),2);

        return new ResponseEntity(errorModelBean, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

}
