package com.kodilla.library.exception;

public class BookCopyChangeStatusException extends RuntimeException {
    public BookCopyChangeStatusException() {
        super("Can't change status");
    }
}
