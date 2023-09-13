package com.example.MiniLibrary.controller;

import com.example.MiniLibrary.model.Books;
import com.example.MiniLibrary.model.BorrowedBooks;
import com.example.MiniLibrary.model.Users;
import com.example.MiniLibrary.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/users")
    public List<Users> listAllUsers() {
        return libraryService.listAllUsers();
    }

    @PostMapping("/users")
    public Users addUser(@RequestBody Users user) {
        return libraryService.addUser(user);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        libraryService.deleteUser(userId);
    }

    @PutMapping("/users")
    public Users updateUser(@RequestBody Users user) {
        return libraryService.updateUser(user);
    }

    @GetMapping("/users/{userId}")
    public Users findUserById(@PathVariable Long userId) {
        return libraryService.findUserById(userId);
    }

    @GetMapping("/users/email/{email}")
    public Users findUserByEmail(@PathVariable String email) {
        return libraryService.findUserByEmail(email).orElse(null);
    }

    @GetMapping("/users/search")
    public List<Users> findUserByFullName(@RequestParam("fullName") String fullName) {
        return libraryService.findUserByFullName(fullName);
    }

    @GetMapping("/books")
    public List<Books> listAllBooks() {
        return libraryService.listAllBooks();
    }

    @PostMapping("/books")
    public Books addBook(@RequestBody Books book) {
        return libraryService.addBook(book);
    }

    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        libraryService.deleteBook(bookId);
    }

    @GetMapping("/books/search")
    public List<Books> searchBooks(@RequestParam("query") String query) {
        return libraryService.searchBooks(query);
    }

    @PostMapping("/borrow/{userId}/{bookId}")
    public BorrowedBooks borrowBook(@PathVariable Long userId, @PathVariable Long bookId) {
        return libraryService.borrowBook(userId, bookId);
    }

    @DeleteMapping("/return/{userId}/{bookId}")
    public void returnBook(@PathVariable Long userId, @PathVariable Long bookId) {
        libraryService.returnBook(userId, bookId);
    }

    @GetMapping("/borrowed/{userId}")
    public List<BorrowedBooks> listBorrowedBooksByUser(@PathVariable Long userId) {
        return libraryService.listBorrowedBooksByUser(userId);
    }
}
