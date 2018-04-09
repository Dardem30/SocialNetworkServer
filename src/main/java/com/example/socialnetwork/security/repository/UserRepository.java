package com.example.socialnetwork.security.repository;


import com.example.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for working with the database.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String userName);
}
