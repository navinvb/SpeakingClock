package com.nktech.speakingclock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SpeakingClockGlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = InvalidInputException.class)
    public ResponseEntity<Object> invalidTimeInput(InvalidInputException invalidInputException) {
        return new ResponseEntity<Object>("Please Enter the valid time format{HH:mm}", HttpStatus.BAD_REQUEST);
    }

}
