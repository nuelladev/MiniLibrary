package com.example.MiniLibrary.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Table(name = "library_users")
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Email(message = "Please provide a valid email address.")
    private String email;

    @Size(min = 6, max = 15, message = "Full name must be between 6 and 15 characters.")
    private String fullName;

    @Min(value = 18, message = "Age must be at least 18.")
    @Max(value = 70, message = "Age cannot exceed 70.")
    private int age;
    @NotBlank(message = "Cannot be blank!!! Please enter your address")
    private String address;



}
