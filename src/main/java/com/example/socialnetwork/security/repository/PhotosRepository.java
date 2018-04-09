package com.example.socialnetwork.security.repository;

import com.example.socialnetwork.model.Photos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotosRepository extends JpaRepository<Photos,Integer> {
    List<Photos> findAllByUserId(int id);
}
