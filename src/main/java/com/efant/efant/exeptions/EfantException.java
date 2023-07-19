package com.efant.efant.exeptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
public class EfantException extends Exception {

    private String errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;
    private Instant time;


    public EfantException(String errorCode, String errorMessage, HttpStatus httpStatus){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
        this.time = Instant.now();
    }








}
