package com.example.socialnetwork.security.controller;


//import com.example.socialnetwork.model.Photos;
import com.example.socialnetwork.model.Photos;
import com.example.socialnetwork.model.User;
import com.example.socialnetwork.security.dto.UserDto;
import com.example.socialnetwork.security.repository.PhotosRepository;
import com.example.socialnetwork.security.service.PhotosService;
import com.example.socialnetwork.security.service.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Rest Controller for User.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    /**
     * Method for create new User.
     *
     * @param userDto UserDto userDto.
     * @return UserDto response from created User.
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody final UserDto userDto) {

        final User userToSave = dozerBeanMapper.map(userDto, User.class);

        final User savedUser = userService.create(userToSave);

        final UserDto response = dozerBeanMapper.map(savedUser, UserDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Method for update User from database.
     *
     * @param userDto UserDto userDto.
     * @return UserDto response from updated User.
     */
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody final UserDto userDto) {

        final User userToUpdate = dozerBeanMapper.map(userDto, User.class);

        final User userToResponse = userService.update(userToUpdate);

        final UserDto response = dozerBeanMapper.map(userToResponse, UserDto.class);

        return ResponseEntity.ok().body(response);
    }
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity fetchAll() {
        final List<UserDto> response = new ArrayList<>();
        userService.listUsers()
                .forEach(user -> response.add(dozerBeanMapper.map(user, UserDto.class)));

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch user by Id.
     *
     * @param userId User userId.
     * @return UserDto response from displayed User.
     */
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public ResponseEntity fetch(@PathVariable final Integer userId) {

        final User userToResponse = userService.fetch(userId);

        final UserDto response = dozerBeanMapper.map(userToResponse, UserDto.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/usersByUsername/{username}", method = RequestMethod.GET)
    public ResponseEntity fetch(@PathVariable String username) {

        final User userToResponse = userService.getUserByUsername(username);

        final UserDto response = dozerBeanMapper.map(userToResponse, UserDto.class);

        return ResponseEntity.ok().body(response);
    }
    /**
     * Method for delete user by Id.
     *
     * @param userId User userId.
     * @return UserDto response from deleted User.
     */
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable final Integer userId) {

        final User userToResponse = userService.fetch(userId);

        userService.delete(userId);

        final UserDto response = dozerBeanMapper.map(userToResponse, UserDto.class);

        return ResponseEntity.ok().body(response);
    }


}
