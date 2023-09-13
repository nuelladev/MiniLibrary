package com.example.MiniLibrary.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowedBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;


    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;


    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_author")
    private String bookAuthor;


}



