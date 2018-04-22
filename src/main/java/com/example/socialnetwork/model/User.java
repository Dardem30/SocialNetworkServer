package com.example.socialnetwork.model;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

/**
 * Entity that describes the user.
 */
@Entity
@Table(name = "users")
@SuppressWarnings("PMD.ShortClassName")
public class User implements UserDetails {

    private static final long serialVersionUID = -4031829348949429069L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "date")
    private Date date;
    @OneToMany()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Photos> photos;
    @OneToMany()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<LikeTable> likeTables;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Authority> authorities;
    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Comments> comments;
    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Answers> answers;
    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Message> messages;
    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Friends> friends;
    @OneToMany
    @JoinColumn(name = "user_name", referencedColumnName = "name")
    private List<Message> messages1;

    public List<Message> getMessages1() {
        return messages1;
    }

    public void setMessages1(List<Message> messages1) {
        this.messages1 = messages1;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
    }

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    @Column(name = "enabled")
    private boolean enabled;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public List<LikeTable> getLikeTables() {
        return likeTables;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public void setLikeTables(List<LikeTable> likeTables) {
        this.likeTables = likeTables;
    }
}
