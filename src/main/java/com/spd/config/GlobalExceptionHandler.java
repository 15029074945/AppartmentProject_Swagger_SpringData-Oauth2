package com.spd.config;

import com.spd.bean.ErrorModelBean;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//@EnableWebMvc
//@ControllerAdvice(basePackages = "com/spd")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorModelBean> handleError(Exception ex) {

        ErrorModelBean errorModelBean = new ErrorModelBean("111" + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(errorModelBean, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = LoginAuthenticationProvider.AuthenticationExceptionImpl.class)
    public ResponseEntity<ErrorModelBean> handleAuthentication(Exception ex) {
        ErrorModelBean errorModelBean = new ErrorModelBean("111" + ex.getMessage(),
                HttpStatus.UNAUTHORIZED.value());

        return new ResponseEntity<>(errorModelBean, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleControllerException(HttpServletRequest req, Exception ex) {
        return new ResponseEntity<>("111", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = MessagingException.class)
    public ResponseEntity<ErrorModelBean> handleVerifyEmail(Exception ex) {
        ErrorModelBean errorModelBean = new ErrorModelBean("111" + "Not send email" + ex.getMessage(),
                HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorModelBean, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("path",request.getContextPath());
        responseBody.put("message","111" + "The URL you have reached is not in service at this time (404).");
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }
/*
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorModelBean> handleError(RuntimeException ex) {

        ErrorModelBean errorModelBean =new ErrorModelBean(ex.getMessage(),2);

        return new ResponseEntity(errorModelBean, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

}
