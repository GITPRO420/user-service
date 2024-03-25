package com.pk.userservice.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TokenExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomTokenException.class)
    public ResponseEntity<ErrorResponse> tokenExceptionHandler(CustomTokenException exception) {
        ErrorResponse error = ErrorResponse.builder().errorCode(exception.getErrorCode()).errorMessage(exception.getErrorMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
