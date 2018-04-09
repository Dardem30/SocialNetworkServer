package com.example.socialnetwork.security.service.detail;



import com.example.socialnetwork.model.User;
import com.example.socialnetwork.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Class that implements the UserDetailsService.
 */
@Service("mainUserDetailsService")
public class DefaultUserDetailsService implements UserDetailsService {
@Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        final Optional<User> user = Optional.ofNullable(userService.getUserByUsername(username));

        if (user.isPresent()) {

            return user.get();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
