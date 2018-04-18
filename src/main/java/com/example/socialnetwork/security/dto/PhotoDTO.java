package com.example.socialnetwork.security.dto;

import com.example.socialnetwork.model.Comments;
import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.List;

@JsonTypeName(value = "user")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty
    int id;
    @JsonProperty
    private byte[] content;
    @JsonProperty
    private Integer userId;
    @JsonProperty
    private int likes;
    @JsonProperty
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
