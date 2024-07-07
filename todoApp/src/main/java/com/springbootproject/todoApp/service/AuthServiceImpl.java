package com.springbootproject.todoApp.service;

import com.springbootproject.todoApp.JwtAuthResponse;
import com.springbootproject.todoApp.dto.LoginDto;
import com.springbootproject.todoApp.dto.RegisterDto;
import com.springbootproject.todoApp.entity.Role;
import com.springbootproject.todoApp.entity.User;
import com.springbootproject.todoApp.exception.TodoAPIException;
import com.springbootproject.todoApp.repository.RoleRepository;
import com.springbootproject.todoApp.repository.UserRepository;
import com.springbootproject.todoApp.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    // used to interact with the database for user-related operations
    private UserRepository userRepository;
    // used to retrieve roles for assigning to users during registration
    private RoleRepository roleRepository;
    // used to securely hash passwords before storing them in the database
    private PasswordEncoder passwordEncoder;
    // responsible for generating JWT tokens for authenticated users
    private JwtTokenProvider jwtTokenProvider;

    // provided by Spring Security to authenticate users based on credentials
    // injected dependency provided by Spring Security. It manages the
    // authentication process based on the configured authentication providers
    private AuthenticationManager authenticationManager;
    @Override
    public String register(RegisterDto registerDto) {

        // check if username already exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        // check if email already exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Email already exists");

        }
        // create a User object and store it in database
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword())); // securely hash passwords
                                                                // before storing them in the database

        // assign ROLE_USER to the user
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);
        user.setRoles(roles);

        // save the user to the database
        userRepository.save(user);
        return "User Register Successfully!";
    }

    @Override
    public JwtAuthResponse login(LoginDto loginDto) {

        // initiates the authentication process by creating an UsernamePasswordAuthenticationToken
        // with the usernameOrEmail and password from LoginDto
        // authenticate method of AuthenticationManager attempts
        // to authenticate the user with the provided credentials
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(),
                        loginDto.getPassword()
                )
        );

        // sets the authenticated Authentication object into the SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // generates a JWT token using jwtTokenProvider.generateToken(authentication) and returns it
        String token = jwtTokenProvider.generateToken(authentication);

        Optional<User> userOptional = userRepository.findByUsernameOrEmail(
                loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail()
        );

        String role = null;

        if(userOptional.isPresent()){
            User loggedInUser = userOptional.get();
            Optional<Role> optionalRole = loggedInUser.getRoles().stream().findFirst();

            if(optionalRole.isPresent()){
                Role userRole = optionalRole.get();
                role = userRole.getName();
            }
        }

        // constructs a JwtAuthResponse object containing the JWT token and the role of the user

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setRole(role);
        jwtAuthResponse.setAccessToken(token);
        return jwtAuthResponse;
    }
}
