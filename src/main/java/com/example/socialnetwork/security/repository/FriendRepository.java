package com.example.socialnetwork.security.repository;

import com.example.socialnetwork.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friends,Integer> {
    List<Friends> findAllByUserId(int id);
}
