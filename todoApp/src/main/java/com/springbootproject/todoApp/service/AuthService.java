package com.springbootproject.todoApp.service;

import com.springbootproject.todoApp.JwtAuthResponse;
import com.springbootproject.todoApp.dto.LoginDto;
import com.springbootproject.todoApp.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);
    JwtAuthResponse login(LoginDto loginDto);
}
