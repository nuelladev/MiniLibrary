package com.example.MiniLibrary.repositories;

import com.example.MiniLibrary.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    List<Users> findByFullNameContaining(String fullName);
}
