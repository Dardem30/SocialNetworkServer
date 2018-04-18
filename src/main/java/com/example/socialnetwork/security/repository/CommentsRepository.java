package com.example.socialnetwork.security.repository;

import com.example.socialnetwork.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments,Integer> {
}
