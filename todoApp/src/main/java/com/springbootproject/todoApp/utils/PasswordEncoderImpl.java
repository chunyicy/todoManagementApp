package com.springbootproject.todoApp.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderImpl {

    public static void main(String[] args){

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Two passwords, "user1" and "admin", are encoded using the passwordEncoder.encode() method

        System.out.println(passwordEncoder.encode("user1"));

        System.out.println(passwordEncoder.encode("admin"));
    }
}
