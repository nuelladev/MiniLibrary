package com.example.MiniLibrary.exceptions;


public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long bookId) {
        super("Book not found with ID: " + bookId);
    }
}
