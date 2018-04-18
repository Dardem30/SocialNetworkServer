package com.example.socialnetwork.security.service;

import com.example.socialnetwork.model.LikeTable;
import com.example.socialnetwork.security.repository.LikeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LikeTableService {
    @Autowired
    private LikeTableRepository likeTableRepository;
    public void deleteLikeById(int id){
        likeTableRepository.deleteByPhotoId(id);
    }
    public void saveLike(LikeTable likeTable){
        likeTableRepository.save(likeTable);
    }
}
