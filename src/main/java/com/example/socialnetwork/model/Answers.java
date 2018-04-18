package com.example.socialnetwork.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "text")
    String text;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "comment_id")
    private Integer commentId;
    @Column(name = "nameuser")
    private String nameuser;
    @Column(name = "surnameuser")
    private String surnameuser;

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

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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
        Answers answers = (Answers) o;
        return id == answers.id &&
                Objects.equals(text, answers.text) &&
                Objects.equals(userId, answers.userId) &&
                Objects.equals(commentId, answers.commentId) &&
                Objects.equals(nameuser, answers.nameuser) &&
                Objects.equals(surnameuser, answers.surnameuser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, text, userId, commentId, nameuser, surnameuser);
    }
}
