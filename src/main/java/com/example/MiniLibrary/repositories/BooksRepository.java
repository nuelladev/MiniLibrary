package com.example.MiniLibrary.repositories;

import com.example.MiniLibrary.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;



public interface BooksRepository extends JpaRepository<Books, Long> {
        Optional<Books> findByIsbn(String isbn);
        List<Books> findByTitleContainingOrAuthorContainingOrIsbnContaining(String title, String author, String isbn);
        List<Books> findByTitleContaining(String title);
        List<Books> findByAuthorContaining(String author);
        List<Books> findByIsbnContaining(String isbn);
        List<Books> findByPublicationYear(int year);
}

