package com.example.socialnetwork.security.controller;

import com.example.socialnetwork.model.Photos;
import com.example.socialnetwork.model.User;
import com.example.socialnetwork.security.dto.UserDto;
import com.example.socialnetwork.security.service.PhotosService;
import com.example.socialnetwork.security.service.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PhotoController {
    @Autowired
    private UserService userService;
    @Autowired
    private PhotosService photosService;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;
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
    public ResponseEntity<Photos> like(@PathVariable int id,@RequestParam("username") String username){

                Photos photos = photosService.findPhotoById(id);
                photos.setLikes(photos.getLikes()+ 1);
                photosService.savePhoto(photos);
                return new ResponseEntity<Photos>(photos, HttpStatus.OK);
    }
}
