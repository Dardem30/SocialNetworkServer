package com.example.socialnetwork.security.dto;

//import com.example.socialnetwork.model.Photos;
import com.example.socialnetwork.model.Friends;
import com.example.socialnetwork.model.LikeTable;
import com.example.socialnetwork.model.Message;
import com.example.socialnetwork.model.Photos;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

/**
 * * Intermediate class of User and controller work.
 */
@JsonTypeName(value = "user")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {

    private static final long serialVersionUID = 5381524464602378056L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String username;
    @JsonProperty
    private String name;
    @JsonProperty
    private String surname;
    @JsonProperty
    private String password;
    @JsonProperty
    private String email;
    @JsonProperty
    private Date date;
    @JsonProperty
    private List<Photos> photos;
    @JsonProperty
    private List<AuthorityDto> authorities;
    @JsonProperty
    private List<LikeTable> likeTables;
    @JsonProperty
    private List<Message> messages;
    @JsonProperty
    private List<Friends> friends;

    public List<Friends> getFriends() {
        return friends;
    }

    public void setFriends(List<Friends> friends) {
        this.friends = friends;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<LikeTable> getLikeTables() {
        return likeTables;
    }

    public void setLikeTables(List<LikeTable> likeTables) {
        this.likeTables = likeTables;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AuthorityDto> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityDto> authorities) {
        this.authorities = authorities;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }

    public Integer getId() {
        return id;
    }
}
