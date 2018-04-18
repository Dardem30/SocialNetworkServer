package com.example.socialnetwork.security.repository;

import com.example.socialnetwork.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
