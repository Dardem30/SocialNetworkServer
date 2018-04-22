package com.example.socialnetwork.security.service;

import com.example.socialnetwork.model.Friends;
import com.example.socialnetwork.model.User;
import com.example.socialnetwork.security.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FriendService {
    @Autowired
    private FriendRepository friendRepository;
    public Friends save(User user,int id){
        Friends friend=new Friends();
        friend.setName(user.getName());
        friend.setUserId(id);
        friendRepository.save(friend);
        return friend;
    }
    public List<Friends> findAllByUserId(int userId){
        return friendRepository.findAllByUserId(userId);
    }
}
