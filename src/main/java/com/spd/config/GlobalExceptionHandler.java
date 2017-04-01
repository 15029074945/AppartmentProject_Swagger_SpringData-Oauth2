package com.spd.config;

import com.spd.bean.ErrorModelBean;
import com.spd.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.mail.MessagingException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String DEFAULT_MESSAGE = "(Global) ";
    private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    /*@ExceptionHandler(value = AuthenticationUserException.class)
    public ResponseEntity<ErrorModelBean> authenticationUserException(Exception ex) {
        return new ResponseEntity<>(new ErrorModelBean(DEFAULT_MESSAGE + ex.getMessage()), DEFAULT_HTTP_STATUS);
    }*/

    @ExceptionHandler(value = AlreadyHaveUserEmail.class)
    public ResponseEntity<ErrorModelBean> alreadyHaveUserEmail(Exception ex) {
        return new ResponseEntity<>(new ErrorModelBean(DEFAULT_MESSAGE + ex.getMessage()), DEFAULT_HTTP_STATUS);
    }

    @ExceptionHandler(value = AlreadyHaveUserTelephone.class)
    public ResponseEntity<ErrorModelBean> alreadyHaveUserTelephone(Exception ex) {
        return new ResponseEntity<>(new ErrorModelBean(DEFAULT_MESSAGE + ex.getMessage()), DEFAULT_HTTP_STATUS);
    }

    @ExceptionHandler(value = CheckFailedException.class)
    public ResponseEntity<ErrorModelBean> checkFailedException(Exception ex) {
        return new ResponseEntity<>(new ErrorModelBean(DEFAULT_MESSAGE + ex.getMessage()), DEFAULT_HTTP_STATUS);
    }

    @ExceptionHandler(value = FilledException.class)
    public ResponseEntity<ErrorModelBean> filledException(Exception ex) {
        return new ResponseEntity<>(new ErrorModelBean(DEFAULT_MESSAGE + ex.getMessage()), DEFAULT_HTTP_STATUS);
    }

    @ExceptionHandler(value = NoSuchAddressException.class)
    public ResponseEntity<ErrorModelBean> noSuchAddressException(Exception ex) {
        return new ResponseEntity<>(new ErrorModelBean(DEFAULT_MESSAGE + ex.getMessage()), DEFAULT_HTTP_STATUS);
    }

    @ExceptionHandler(value = NoSuchAnnouncementException.class)
    public ResponseEntity<ErrorModelBean> noSuchAnnouncementException(Exception ex) {
        return new ResponseEntity<>(new ErrorModelBean(DEFAULT_MESSAGE + ex.getMessage()), DEFAULT_HTTP_STATUS);
    }

    @ExceptionHandler(value = NoSuchUserException.class)
    public ResponseEntity<ErrorModelBean> noSuchUserException(Exception ex) {
        return new ResponseEntity<>(new ErrorModelBean(DEFAULT_MESSAGE + ex.getMessage()), DEFAULT_HTTP_STATUS);
    }

    @ExceptionHandler(value = NoSuchUserEmailException.class)
    public ResponseEntity<ErrorModelBean> noSuchUserEmailException(Exception ex) {
        return new ResponseEntity<>(new ErrorModelBean(DEFAULT_MESSAGE + ex.getMessage()), DEFAULT_HTTP_STATUS);
    }

    @ExceptionHandler(value = UserAuthenticationException.class)
    public ResponseEntity<ErrorModelBean> userAuthenticationException(Exception ex) {
        return new ResponseEntity<>(new ErrorModelBean(DEFAULT_MESSAGE + ex.getMessage()), DEFAULT_HTTP_STATUS);
    }

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<ErrorModelBean> validationException(Exception ex) {
        return new ResponseEntity<>(new ErrorModelBean(DEFAULT_MESSAGE + ex.getMessage()), DEFAULT_HTTP_STATUS);
    }

    @ExceptionHandler(value = MessagingUserException.class)
    public ResponseEntity<ErrorModelBean> handleVerifyEmail(Exception ex) {
        ErrorModelBean errorModelBean = new ErrorModelBean(DEFAULT_MESSAGE + "Not send email" + ex.getMessage());

        return new ResponseEntity<>(errorModelBean, HttpStatus.OK);
    }

    @ExceptionHandler(value = ImageException.class)
    public ResponseEntity<ErrorModelBean> handleErrorFileFormat(ImageException ex) {

        ErrorModelBean errorModelBean = new ErrorModelBean("Image should have jpeg,png or gif file extension." +
                "Not more then 2МБ. Min image resolution: 800X600 .Max image resolution: 2048X1536  "+ ex.getMessage());

        return new ResponseEntity<>(errorModelBean, HttpStatus.BAD_REQUEST);
    }

}
