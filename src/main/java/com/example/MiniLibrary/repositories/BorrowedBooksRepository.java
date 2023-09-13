package com.example.MiniLibrary.repositories;

import com.example.MiniLibrary.model.BorrowedBooks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, Long> {
    List<BorrowedBooks> findByUserId(Long userId);
    Optional<BorrowedBooks> findByBookNameAndBookAuthor(String bookName, String bookAuthor);

    Optional<BorrowedBooks> findByUserIdAndBookId(Long userId, Long bookId);
}

