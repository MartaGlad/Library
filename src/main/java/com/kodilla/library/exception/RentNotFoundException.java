package com.kodilla.library.exception;

public class RentNotFoundException extends RuntimeException {

    public RentNotFoundException(Long id) {
        super("Rent with id " + id + " not found.");
    }
}
