package com.example.socialnetwork.security.service;


import com.example.socialnetwork.model.Authority;
import com.example.socialnetwork.security.repository.AuthorityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class that implements the Authority's work and databases.
 */
@Service
@Slf4j
public class DefaultAuthorityService implements AuthorityService {
@Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority create(final Authority authority) {


        return authorityRepository.save(authority);
    }

    @Override
    public Authority update(final Authority authority) {

        final Authority saved = authorityRepository.getOne(authority.getId());

        authority.setAuthority(authority.getAuthority() == null
                ? saved.getAuthority() : authority.getAuthority());
        authority.setUserId(saved.getUserId());

        return authorityRepository.save(authority);
    }
}
