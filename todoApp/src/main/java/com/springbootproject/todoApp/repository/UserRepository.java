package com.springbootproject.todoApp.repository;

import com.springbootproject.todoApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // this method declares a query method to find a User by their username
    Optional<User> findByUsername(String username);

    // this method checks if a user exists with a given email
    Boolean existsByEmail(String email);

    // this method finds a user by either username or email
    Optional<User> findByUsernameOrEmail(String username, String email);

    // this method checks if user exists with given username
    Boolean existsByUsername(String username);
}
