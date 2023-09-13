package com.example.MiniLibrary.exceptions;

public class BorrowedBookNotFoundException extends RuntimeException {
    public BorrowedBookNotFoundException(Long userId, Long borrowedBookId) {
        super("Borrowed book not found with ID: " + borrowedBookId);
    }
}

