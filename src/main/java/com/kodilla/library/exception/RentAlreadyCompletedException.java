package com.kodilla.library.exception;

public class RentAlreadyCompletedException extends RuntimeException {
    public RentAlreadyCompletedException() {
        super("Rent already completed");
    }
}
