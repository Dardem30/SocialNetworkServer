package com.example.socialnetwork.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "photos")
public class Photos {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="content", nullable=false)
    private byte[] content;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "likes")
    private int likes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
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
        Photos photos = (Photos) o;
        return id == photos.id &&
                likes == photos.likes &&
                Arrays.equals(content, photos.content);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, likes);
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }

    public int getLikes() {

        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

}
