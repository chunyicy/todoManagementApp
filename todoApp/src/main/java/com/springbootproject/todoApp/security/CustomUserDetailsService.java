package com.springbootproject.todoApp.security;

import com.springbootproject.todoApp.entity.User;
import com.springbootproject.todoApp.exception.ResourceNotFoundException;
import com.springbootproject.todoApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


// The CustomUserDetailsService class integrates with Spring Security to provide custom logic for
// loading user details from a database (via UserRepository). It retrieves user information based
// on username or email, maps user roles to Spring Security GrantedAuthority objects, and constructs
// a UserDetails object used for authentication and authorization within a Spring Security-enabled application.
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        // queries the database using UserRepository to find a user by either username or email
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(()-> new ResourceNotFoundException("User not exists by Username or Email"));

        // converted a set of roles into a set of GrantedAuthority
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        // creating UserDetails Object:
        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                user.getPassword(),
                authorities
        );
    }
}




