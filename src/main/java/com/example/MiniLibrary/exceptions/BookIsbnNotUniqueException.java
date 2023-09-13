package com.example.MiniLibrary.exceptions;

public class BookIsbnNotUniqueException extends RuntimeException {
    public BookIsbnNotUniqueException(String isbn) {
        super("A book with ISBN " + isbn + " already exists.");
    }
}

