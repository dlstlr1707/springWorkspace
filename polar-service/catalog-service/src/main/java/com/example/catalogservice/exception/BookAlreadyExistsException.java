package com.example.catalogservice.exception;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String isbn) {
        super("Book is already exist " + isbn);
    }
}
