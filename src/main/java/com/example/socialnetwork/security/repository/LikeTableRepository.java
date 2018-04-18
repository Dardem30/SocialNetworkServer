package com.example.socialnetwork.security.repository;

import com.example.socialnetwork.model.LikeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeTableRepository extends JpaRepository<LikeTable,Integer>{
    void deleteByPhotoId(int photoId);
}
