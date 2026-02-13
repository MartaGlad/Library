package com.kodilla.library.exception;

public class BookCopyNotFoundException extends RuntimeException {

    public BookCopyNotFoundException(Long id) {
        super("Book copy with id " + id + "not found");
    }

}
