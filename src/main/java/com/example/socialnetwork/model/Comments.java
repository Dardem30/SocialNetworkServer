package com.example.socialnetwork.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "text")
    String text;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "photo_id")
    private Integer photoId;
    @Column(name = "nameuser")
    private String nameuser;
    @Column(name = "surnameuser")
    private String surnameuser;
    @OneToMany
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private List<Answers> answers;

    public List<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getNameuser() {
        return nameuser;
    }

    public void setNameuser(String nameuser) {
        this.nameuser = nameuser;
    }

    public String getSurnameuser() {
        return surnameuser;
    }

    public void setSurnameuser(String surnameuser) {
        this.surnameuser = surnameuser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments = (Comments) o;
        return id == comments.id &&
                Objects.equals(text, comments.text) &&
                Objects.equals(userId, comments.userId) &&
                Objects.equals(photoId, comments.photoId) &&
                Objects.equals(nameuser, comments.nameuser) &&
                Objects.equals(surnameuser, comments.surnameuser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, text, userId, photoId, nameuser, surnameuser);
    }
}
