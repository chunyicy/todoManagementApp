package com.springbootproject.todoApp;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthResponse {

    private String accessToken; // represents the JWT access token generated

    // represents the type of the token, which is typically "Bearer" for JWT tokens
    // used in Authorization headers
    private String tokenType = "Bearer";

    private String role;

}
