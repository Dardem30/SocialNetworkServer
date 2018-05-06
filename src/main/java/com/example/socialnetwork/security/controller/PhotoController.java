package com.example.socialnetwork.security.controller;

import com.example.socialnetwork.model.*;
import com.example.socialnetwork.security.dto.PhotoDTO;
import com.example.socialnetwork.security.dto.UserDto;
import com.example.socialnetwork.security.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class PhotoController {
    @Autowired
    private UserService userService;
    @Autowired
    private PhotosService photosService;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    @Autowired
    private LikeTableService likeTableService;
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private AnswersService answersService;
    @Autowired
    private FriendService friendService;
    @PostMapping(value = "/photo",headers = "content-type=multipart/*")
    public ResponseEntity<User> savePhoto(@RequestParam("file")MultipartFile multipartFile,@RequestParam("username") String username) throws IOException {
        Photos photos=new Photos();
        User user=userService.getUserByUsername(username);
        photos.setUserId(user.getId());
        photosService.savePhotoWithImage(photos,multipartFile);
        UserDto userDto=dozerBeanMapper.map(user,UserDto.class);
        List<Photos> photosList=new ArrayList<>();
        photosList.add(photos);
        for(Photos photos1: user.getPhotos()){
            photosList.add(photos1);
        }
        userDto.setPhotos(photosList);

        final User userToUpdate = dozerBeanMapper.map(userDto, User.class);
        userToUpdate.setId(userService.getUserByUsername(username).getId());
        final User userToResponse = userService.update(userToUpdate);
return new ResponseEntity<User>(userToResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/userPhoto/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<Photos>> fetchPhotos(@PathVariable final Integer userId) {
        return ResponseEntity.ok().body(photosService.fetchPhotosOfUser(userId));
    }
    @PostMapping(value = "/photo/like/{id}")
    public ResponseEntity<Boolean> like(@PathVariable int id,@RequestParam("username") String username){
        User user=userService.getUserByUsername(username);
        for(LikeTable likeTable:user.getLikeTables()){
            if(likeTable.getPhotoId()==id){
                Photos photos = photosService.findPhotoById(id);
                photos.setLikes(photos.getLikes()- 1);
                photosService.savePhoto(photos);
                likeTableService.deleteLikeById(id);
                return new ResponseEntity(false, HttpStatus.OK);
            }
        }
        Photos photos = photosService.findPhotoById(id);
        photos.setLikes(photos.getLikes()+ 1);
        photosService.savePhoto(photos);
        LikeTable likeTable=new LikeTable();
        likeTable.setPhotoId(id);
        likeTable.setUserId(user.getId());
        likeTableService.saveLike(likeTable);
        return new ResponseEntity(true, HttpStatus.OK);
    }
    @GetMapping(value = "/photo/{photoId}")
    public ResponseEntity<PhotoDTO> getPhoto(@PathVariable int photoId){
        Photos photos=photosService.findPhotoById(photoId);
        PhotoDTO response = dozerBeanMapper.map(photos, PhotoDTO.class);
        return new ResponseEntity<PhotoDTO>(response,HttpStatus.OK);
    }
    @PostMapping(value = "/comment/{photoId}")
    public ResponseEntity<Comments> save(@PathVariable int photoId, @RequestParam("username") String username, @RequestParam("text")String text){
        User user=userService.getUserByUsername(username);
        Comments comments=new Comments();
        comments.setText(text);
        comments.setPhotoId(photosService.findPhotoById(photoId).getId());
        comments.setUserId(user.getId());
        comments.setNameuser(user.getName());
        comments.setSurnameuser(user.getSurname());
        commentsService.saveComment(comments);
        return new ResponseEntity<Comments>(comments, HttpStatus.OK);
    }
    @PostMapping(value = "/answer/{commentId}")
    public ResponseEntity<Answers> saveAns(@PathVariable int commentId,@RequestParam("username") String username, @RequestParam("text")String text){
        User user=userService.getUserByUsername(username);
        Answers answers=new Answers();
        answers.setCommentId(commentId);
        answers.setText(text);
        answers.setUserId(user.getId());
        answers.setNameuser(user.getName());
        answers.setSurnameuser(user.getSurname());
        answersService.saveAnswer(answers);
        return new ResponseEntity<Answers>(answers,HttpStatus.OK);

    }
    @GetMapping(value = "/friendPhoto/{friendId}")
    public ResponseEntity<List<Photos>> friendPhoto(@PathVariable int friendId){
        Optional<Friends> friends=friendService.findById(friendId);
        User user=userService.findByName(friends.get().getName());
        return new ResponseEntity<List<Photos>>(photosService.fetchPhotosOfUser(user.getId()),HttpStatus.OK);
    }
}
