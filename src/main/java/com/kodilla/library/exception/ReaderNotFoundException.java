package com.kodilla.library.exception;

public class ReaderNotFoundException extends RuntimeException {
    public ReaderNotFoundException() {
        super("Reader not found");
    }
}
