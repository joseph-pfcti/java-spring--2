package com.pfcti.springwebservices.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This is a custom package to handle exceptions. This style of component is called AOP Aspect oriented programming
 * Only one @ControllerAdvice can be executed to handle exceptions
 * If you want to use MetadataRestExceptionsHandler the GlobalExceptionHandler cannot be enabled at the same time and the same applies to the opposite
 */

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
