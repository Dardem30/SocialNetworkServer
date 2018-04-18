package com.example.socialnetwork.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class LikeTable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "photo_id")
    int photoId;
    @Column(name = "user_id")
    private Integer userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeTable likeTable = (LikeTable) o;
        return id == likeTable.id &&
                photoId == likeTable.photoId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, photoId);
    }
}
