package com.example.MiniLibrary.exceptions;

public class BookAlreadyBorrowedException extends RuntimeException {
    public BookAlreadyBorrowedException(Long userId, Long bookId) {
        super("Book with ID " + bookId + " is already borrowed by user with ID " + userId);
    }
}

