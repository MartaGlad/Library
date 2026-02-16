package com.kodilla.library.exception;

public class RentAlreadyCompletedException extends RuntimeException {

    public RentAlreadyCompletedException(Long rentId) {
        super("Rent with id " + rentId + " already completed.");
    }
}
