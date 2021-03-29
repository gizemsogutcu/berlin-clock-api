package com.api.berlinclock.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<APIResponse> handleAllExceptions(Exception ex) {
        APIResponse apiResponse = new APIResponse();

        apiResponse.setSuccess(false);
        apiResponse.setErrorMessage(ex.getMessage());

        return new ResponseEntity(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
