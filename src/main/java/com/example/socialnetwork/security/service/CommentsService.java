package com.example.socialnetwork.security.service;

import com.example.socialnetwork.model.Comments;
import com.example.socialnetwork.security.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CommentsService {
    @Autowired
    private CommentsRepository commentsRepository;
    public void saveComment(Comments comments){
        commentsRepository.save(comments);
    }
}
