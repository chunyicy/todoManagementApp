package com.springbootproject.todoApp.controller;


import com.springbootproject.todoApp.JwtAuthResponse;
import com.springbootproject.todoApp.dto.LoginDto;
import com.springbootproject.todoApp.dto.RegisterDto;
import com.springbootproject.todoApp.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // this class is a REST controller that handles HTTP requests
@AllArgsConstructor // generates a constructor with arguments for all fields in the class
@RequestMapping("api/auth") // base URL
public class AuthController {

    // dependency injection of AuthService, which is presumably implemented to handle registration logic.
    private AuthService authService;

    // Build Register REST API
    @PostMapping("/register") // maps HTTP POST requests to /api/auth/register
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        // returns an HTTP response with the response string returned by authService.register(), and
        // sets the HTTP status code to HttpStatus.CREATED (HTTP 201 Created)
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    // Build Login REST API
    @PostMapping("login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){

        JwtAuthResponse jwtAuthResponse = authService.login(loginDto);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);

    }


}
