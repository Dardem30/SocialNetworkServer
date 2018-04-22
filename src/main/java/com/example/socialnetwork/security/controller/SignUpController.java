package com.example.socialnetwork.security.controller;


import com.example.socialnetwork.model.User;
import com.example.socialnetwork.security.dto.UserDto;
import com.example.socialnetwork.security.service.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Rest Controller for.
 */
@RestController
public class SignUpController {
    @Autowired
    private UserService defaultUserService;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    /**
     * Method for create User.
     *
     * @param dto UserDto dto.
     * @return UserDto response from created User.
     */
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody final UserDto dto) {
        Date date = new Date();
        dto.setDate(date);
        final User userToSave = dozerBeanMapper.map(dto, User.class);

        final User userToResponse = defaultUserService.create(userToSave);

        final UserDto response = dozerBeanMapper.map(userToResponse, UserDto.class);

        return ResponseEntity.ok().body(response);
    }
}
