package com.example.socialnetwork.security.service;

import com.example.socialnetwork.model.Photos;
import com.example.socialnetwork.security.repository.PhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class PhotosService {
    @Autowired
    private PhotosRepository photosRepository;
    public void savePhotoWithImage(Photos photos, MultipartFile file) throws IOException {
        photos.setContent(file.getBytes());
        photosRepository.save(photos);
    }
    public void savePhoto(Photos photos){
        photosRepository.save(photos);
    }
    public List<Photos> fetchPhotosOfUser(int id){
        return photosRepository.findAllByUserId(id);
    }
    public Photos findPhotoById(int id){
        return photosRepository.getOne(id);
    }
}
