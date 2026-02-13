package com.kodilla.library.exception;

public class ReaderNotFoundException extends RuntimeException {

    public ReaderNotFoundException(Long id) {
        super("Reader with id " + id + "not found");
    }
}
