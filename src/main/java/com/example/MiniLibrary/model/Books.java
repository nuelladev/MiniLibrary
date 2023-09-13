package com.example.MiniLibrary.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.*;

@Table(name = "books")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title is required!!!")
    @Size(min = 1, max = 255, message = "Title must be between {min} and {max} characters")
    private String title;

    @NotNull(message = "Author is required")
    @Size(min = 1, max = 255, message = "Author cannot be blank!!!")
    private String author;

    @Pattern(regexp = "\\d{10}|\\d{13}", message = "Invalid ISBN format")
    private String isbn;

    @Min(value = 1000, message = "Publication year must be at least {value}")
    @Max(value = 2023, message = "Publication year cannot be more than {value}")
    private int publicationYear;
}
