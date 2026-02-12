package com.kodilla.library.exception;

public class RentNotFoundException extends RuntimeException {
    public RentNotFoundException() {
        super("Rent not found");
    }
}
