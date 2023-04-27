package com.pfcti.springwebservices.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice(basePackages = "com.pfcti.springwebservices.api")
public class MetadataRestExceptionsHandler {

    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        String message = exception.getMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ErrorResponse(message, httpStatus.value()), httpStatus);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e) {
        return handleException(e);
    }
}
