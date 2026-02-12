package com.kodilla.library.exception;

public class BookCopyNotFoundException extends RuntimeException {
    public BookCopyNotFoundException() {
        super("BookCopy not found");
    }
}
