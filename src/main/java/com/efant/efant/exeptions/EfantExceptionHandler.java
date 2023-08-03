package com.efant.efant.exeptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class EfantExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(EfantExceptionHandler.class);


    @ExceptionHandler(value = {Exception.class, RuntimeException.class, MethodArgumentTypeMismatchException.class, UsernameNotFoundException.class, AuthenticationException.class})
    public ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        logger.error("Error in Rest App", ex);

        EfantErrorResponse error = new EfantErrorResponse();

        if (ex instanceof EfantException) {
            EfantException efantException = (EfantException) ex;
            error.setHttpStatus(efantException.getHttpStatus().value());
            error.setErrorMessage(efantException.getHttpStatus().getReasonPhrase());
            error.setErrorMessage(efantException.getErrorMessage());
            error.setErrorCode(efantException.getErrorCode());
            error.setTime(efantException.getTime());
        } else {
            error.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            error.setHttpMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            error.setErrorMessage(ex.getMessage());
            error.setErrorMessage("INTERNAL_SERVER_ERROR");
            error.setTime(Instant.now());
        }

        return ResponseEntity.status(error.getHttpStatus()).body(error);


    }
}
