package com.example.MiniLibrary.service;

import com.example.MiniLibrary.exceptions.*;
import com.example.MiniLibrary.repositories.BooksRepository;
import com.example.MiniLibrary.repositories.BorrowedBooksRepository;
import com.example.MiniLibrary.repositories.UsersRepository;
import com.example.MiniLibrary.model.Books;
import com.example.MiniLibrary.model.BorrowedBooks;
import com.example.MiniLibrary.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private BorrowedBooksRepository borrowedBooksRepository;

    private FileWriter activityLogger;

    public LibraryService() {
        try {
            // Specify the full path to the activity.log file
            activityLogger = new FileWriter("C:\\Users\\admin\\Desktop\\MiniLibrary\\src\\main\\java\\com\\example\\MiniLibrary\\activity.log", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // User-related operations

    @Cacheable("getAllUsers")
    public List<Users> listAllUsers() {
        return usersRepository.findAll();
    }

    @CacheEvict(value = "getAllUsers", allEntries = true)
    public Users addUser(Users user) {
        validateUser(user);
        Users savedUser = usersRepository.save(user);
        logActivity("User added: " + savedUser.getId());
        return savedUser;
    }

    @CacheEvict(value = "getAllUsers", allEntries = true)
    public void deleteUser(Long userId) {
        if (!usersRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        usersRepository.deleteById(userId);
        logActivity("User deleted: " + userId);
    }

    @CacheEvict(value = "getAllUsers", allEntries = true)
    public Users updateUser(Users updatedUser) {
        validateUser(updatedUser);
        if (!usersRepository.existsById(updatedUser.getId())) {
            throw new UserNotFoundException(updatedUser.getId());
        }

        Users savedUser = usersRepository.save(updatedUser);
        logActivity("User updated: " + savedUser.getId());
        return savedUser;
    }

    @Cacheable("findUserById")
    public Users findUserById(Long userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Cacheable("findUserByEmail")
    public Optional<Users> findUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Cacheable("findUserByFullName")
    public List<Users> findUserByFullName(String fullName) {
        return usersRepository.findByFullNameContaining(fullName);
    }

    // Book-related operations

    @CacheEvict(value = "getAllBooks", allEntries = true)
    public Books addBook(Books book) {
        // Validate book details
        validateBook(book);
        if (booksRepository.findByIsbn(book.getIsbn()).isPresent()) {
            throw new BookIsbnNotUniqueException(book.getIsbn());
        }

        Books savedBook = booksRepository.save(book);
        logActivity("Book added: " + savedBook.getId());
        return savedBook;
    }

    @CacheEvict(value = "getAllBooks", allEntries = true)
    public void deleteBook(Long bookId) {
        // Check if book exists
        if (!booksRepository.existsById(bookId)) {
            throw new BookNotFoundException(bookId);
        }

        booksRepository.deleteById(bookId);
        logActivity("Book deleted: " + bookId);
    }

    @Cacheable("searchBooks")
    public List<Books> searchBooks(String query) {
        return booksRepository.findByTitleContainingOrAuthorContainingOrIsbnContaining(query, query, query);
    }

    @Cacheable("getAllBooks")
    public List<Books> listAllBooks() {
        return booksRepository.findAll();
    }

    // Borrowed Book-related operations

    @CacheEvict(value = "listBorrowedBooksByUser", key = "#userId")
    public BorrowedBooks borrowBook(Long userId, Long bookId) {
        Users user = findUserById(userId);
        Books book = findBookById(bookId);

        // Check if the book is already borrowed by the user
        if (borrowedBooksRepository.findByUserIdAndBookId(userId, bookId).isPresent()) {
            throw new BookAlreadyBorrowedException(userId, bookId);
        }

        BorrowedBooks borrowedBooks = new BorrowedBooks();
        borrowedBooks.setUser(user);
        borrowedBooks.setBook(book);
        borrowedBooksRepository.save(borrowedBooks);

        logActivity("Book borrowed by user: " + userId + ", Book: " + bookId);
        return borrowedBooks;
    }

    @CacheEvict(value = "listBorrowedBooksByUser", key = "#userId")
    public void returnBook(Long userId, Long bookId) {
        Optional<BorrowedBooks> borrowedBooksOpt = borrowedBooksRepository.findByUserIdAndBookId(userId, bookId);

        if (!borrowedBooksOpt.isPresent()) {
            throw new BorrowedBookNotFoundException(userId, bookId);
        }

        BorrowedBooks borrowedBooks = borrowedBooksOpt.get();
        borrowedBooksRepository.delete(borrowedBooks);

        logActivity("Book returned by user: " + userId + ", Book: " + bookId);
    }

    @Cacheable("listBorrowedBooksByUser")
    public List<BorrowedBooks> listBorrowedBooksByUser(Long userId) {
        return borrowedBooksRepository.findByUserId(userId);
    }

    private void validateUser(Users user) {
        if (user.getEmail() == null || !user.getEmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (user.getAge() < 18 || user.getAge() > 70) {
            throw new IllegalArgumentException("Age must be between 18 and 70");
        }
    }

    private void validateBook(Books book) {
        if (book.getIsbn() == null || !book.getIsbn().matches("\\d{13}")) {
            throw new IllegalArgumentException("Invalid ISBN format");
        }
    }

    private Books findBookById(Long bookId) {
        return booksRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
    }

    private void logActivity(String activity) {
        try {
            activityLogger.write(new Date() + " - " + activity + "\n");
            activityLogger.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
