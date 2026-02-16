package com.kodilla.library.controller.errorhandler;

import com.kodilla.library.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHttpErrorHandler {

    @ExceptionHandler({
            RentAlreadyCompletedException.class,
            BookCopyChangeStatusException.class
    })
    public ResponseEntity<String> handleBadRequestException(RuntimeException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({
            BookCopyNotFoundException.class,
            BookNotFoundException.class,
            ReaderNotFoundException.class,
            RentNotFoundException.class
    })
    public ResponseEntity<String> handleNotFoundException(RuntimeException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleInternalServerErrorException(Exception e) {

        return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


