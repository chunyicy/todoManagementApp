package com.springbootproject.todoApp.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto { // LoginDto class to transfer data between client and server

    // represents the username or email address of the user trying to log in
    private String usernameOrEmail;
    // represents the password associated with the username or email for authentication purposes
    private String password;
}
