package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerAllException(Exception ex, WebRequest request) {
        ResponseDetail<ResponseDetail<Object>> responseDetail = new ResponseDetail<>(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(responseDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

