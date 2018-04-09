package com.example.socialnetwork.security.service.jwt;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for Authentication work.
 */
public interface TokenAuthenticationService {

    Authentication authenticate(HttpServletRequest request);
}
