package com.example.socialnetwork.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Entity that describes the authority.
 */
@Entity
@Table(name = "authorities")
@SuppressWarnings("PMD.AvoidFieldNameMatchingTypeName")
public class Authority implements GrantedAuthority {

    private static final long serialVersionUID = -4031273181701977660L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Integer id;

    @Column(name = "authority")
    private String authority;

    @Column(name = "user_id")
    private Integer userId;

    @Override
    public String getAuthority() {
        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
