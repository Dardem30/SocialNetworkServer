package com.example.socialnetwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@JsonSerialize
@Table(name = "photos")
public class Photos implements Serializable{
    private static final long serialVersionUID = -417662671177241759L;

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
    @OneToMany
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private List<Comments> comments;
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

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
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
