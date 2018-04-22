package com.example.socialnetwork.security.service;



import com.example.socialnetwork.model.Authority;
import com.example.socialnetwork.model.AuthorityType;
import com.example.socialnetwork.model.User;
import com.example.socialnetwork.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

/**
 * Class that implements the User's work and databases.
 */
@Service
@Slf4j
public class DefaultUserService implements UserService {
@Autowired
    private UserRepository userRepository;
@Autowired
    private AuthorityService authorityService;

    @Override
    public User create(final User user) {


        Authority authority = new Authority();
        authority.setAuthority(AuthorityType.PAT.toString());

        authority = authorityService.create(authority);

        user.setAuthorities(Collections.singletonList(authority));
        return userRepository.save(user);
    }

    @Override
    public User update(final User user) {

        final User saved = userRepository.getOne(user.getId());

        user.setUsername(saved.getUsername());

        user.setAuthorities(saved.getAuthorities());

        user.setPassword(user.getPassword() == null ? saved.getPassword() : user.getPassword());

        return userRepository.save(user);
    }

    @Override
    public void delete(final Integer id) {


        userRepository.delete(userRepository.getOne(id));
    }

    @Override
    public User getUserByUsername(final String username) {


        User getUser = userRepository.findByUsername(username);

        return getUser;
    }

    @Override
    public User fetch(final Integer id) {


        return userRepository.getOne(id);
    }

    @Override
    public List<User> listUsers() {


        return userRepository.findAll();
    }
    @Override
    public List<User> listUsersByName(String name) {


        return userRepository.findAllByName(name);
    }
    @Override
    public User findByName(String name){
        return userRepository.findByName(name);
    }



}
