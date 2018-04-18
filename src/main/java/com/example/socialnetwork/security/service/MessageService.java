package com.example.socialnetwork.security.service;

import com.example.socialnetwork.model.Message;
import com.example.socialnetwork.security.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    public void saveMessage(Message message){
        messageRepository.save(message);
    }
}
