package com.kodilla.library.exception;

import com.kodilla.library.domain.Status;

public class BookCopyChangeStatusException extends RuntimeException {

    public BookCopyChangeStatusException() {
        super("Can't change status. Book unavailable.");
    }


    public BookCopyChangeStatusException(Status currentStatus, Status newStatus) {
        super("Can't change " + currentStatus + " to " + newStatus + ".");
    }

}
