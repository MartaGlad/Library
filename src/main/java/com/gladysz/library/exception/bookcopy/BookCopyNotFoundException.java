package com.gladysz.library.exception.bookcopy;

public class BookCopyNotFoundException extends RuntimeException {

    public BookCopyNotFoundException(Long id) {
        super("Book copy with id " + id + " not found.");
    }

}
