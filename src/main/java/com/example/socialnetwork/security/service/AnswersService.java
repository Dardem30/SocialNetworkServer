package com.example.socialnetwork.security.service;

import com.example.socialnetwork.model.Answers;
import com.example.socialnetwork.security.repository.AnswersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AnswersService {
    @Autowired
    private AnswersRepository answersRepository;
    public  void saveAnswer(Answers answers){
        answersRepository.save(answers);
    }
}
