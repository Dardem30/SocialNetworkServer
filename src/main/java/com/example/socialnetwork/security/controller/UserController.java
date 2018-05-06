package com.example.socialnetwork.security.controller;


//import com.example.socialnetwork.model.Photos;
import com.example.socialnetwork.model.Friends;
import com.example.socialnetwork.model.Message;
import com.example.socialnetwork.model.Photos;
import com.example.socialnetwork.model.User;
import com.example.socialnetwork.security.dto.UserDto;
import com.example.socialnetwork.security.repository.PhotosRepository;
import com.example.socialnetwork.security.service.FriendService;
import com.example.socialnetwork.security.service.MessageService;
import com.example.socialnetwork.security.service.PhotosService;
import com.example.socialnetwork.security.service.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    @Autowired
    private MessageService messageService;
    @Autowired
    private FriendService friendService;
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody final UserDto userDto) {

        final User userToSave = dozerBeanMapper.map(userDto, User.class);

        final User savedUser = userService.create(userToSave);

        final UserDto response = dozerBeanMapper.map(savedUser, UserDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


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

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable final Integer userId) {

        final User userToResponse = userService.fetch(userId);

        userService.delete(userId);

        final UserDto response = dozerBeanMapper.map(userToResponse, UserDto.class);

        return ResponseEntity.ok().body(response);
    }
    @RequestMapping(value = "/usersName/{name}", method = RequestMethod.GET)
    public ResponseEntity fetchAllByName(@PathVariable String name) {
        final List<UserDto> response = new ArrayList<>();
        userService.listUsersByName(name)
                .forEach(user ->  response.add(dozerBeanMapper.map(user, UserDto.class)));

        return ResponseEntity.ok().body(response);
    }
    @PostMapping(value = "/sendMessage/{userId}")
    public ResponseEntity<Message> saveMessage(@PathVariable int userId,@RequestParam("text") String text,@RequestParam("name") String name){
        Message message=new Message();
        message.setText(text);
        message.setUserId(userId);
        message.setUserName(name);
        message.setDate(new Date());
        messageService.saveMessage(message);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }
    @GetMapping(value = "/dialogTree/{userId}/{userName}")
    public ResponseEntity<List<Message>> messages(@PathVariable int userId, @PathVariable String userName){
        User user=userService.findByName(userName);
        User user1=userService.fetch(userId);
        List<Message> messageList=new ArrayList<>();
        user.getMessages1().forEach(message -> messageList.add(message));
        user1.getMessages1().forEach(message -> messageList.add(message));
        Collections.sort(messageList);
        Collections.reverse(messageList);
        Collections.reverse(messageList);
        return new ResponseEntity<List<Message>>(messageList,HttpStatus.OK);
    }
    @PostMapping(value = "/addFriend/{friendId}")
    public ResponseEntity<Friends> addFriend(@PathVariable int friendId, @RequestParam("username") String username){
        User user=userService.getUserByUsername(username);
         return new ResponseEntity<Friends>(friendService.save(user,friendId),HttpStatus.OK);
    }
    @PostMapping(value = "/confirm/{name}")
    public ResponseEntity<Friends> addFriend(@PathVariable String name, @RequestParam("username") String username){
        User user=userService.getUserByUsername(username);
        User user1=userService.findByName(name);
         return new ResponseEntity<Friends>(friendService.save(user,user1.getId()),HttpStatus.OK);
    }
    @GetMapping(value = "/findAllUnconfermdFriend/{userId}")
    public ResponseEntity<List<Friends>> listUncomfermedFriends(@PathVariable int userId){
        int c=0;
        List<Friends> response=new ArrayList<>();
        List<Friends> friendsList=friendService.findAllByUserId(userId);
        for(Friends friends: friendsList){
            User user=userService.findByName(friends.getName());
            List<Friends> friendsList1=friendService.findAllByUserId(user.getId());
            for(Friends friends1: friendsList1){
                Optional<User> user1 =userService.findById(userId);
                if(friends1.getName().equals(user1.get().getName())
                        && friends1.getUserId()==user.getId()){
                    c++;
                }
            }
            if(c==0){
                response.add(friends);
            }else{
                c=0;
            }
        }
        return new ResponseEntity<List<Friends>>(response,HttpStatus.OK);
    }
    @GetMapping(value = "/findAllFriends/{userId}")
    public ResponseEntity<List<Friends>> listFriends(@PathVariable int userId){
        return new ResponseEntity<List<Friends>>(friendService.findAllByUserId(userId),HttpStatus.OK);
    }
}