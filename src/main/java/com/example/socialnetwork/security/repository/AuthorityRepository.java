package com.example.socialnetwork.security.repository;


import com.example.socialnetwork.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for working with the database.
 */
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
