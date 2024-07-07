package com.springbootproject.todoApp.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component // indicates that this class is a Spring component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // method 'commence' is called by spring security when an unauthenticated
    // user tries to access a secured resource
    @Override            // represents HTTP request made by the client
    public void commence(HttpServletRequest request,
                         // represents the HTTP response that will be sent to the client
                         HttpServletResponse response,
                         // represents the authentication exception that occured
                         AuthenticationException authException) throws IOException, ServletException {


        // sends an HTTP error response with status code 401 Unauthorized to the client
        // authException.getMessage() provides a descriptive message about why the authentication
        // failed, which helps in debugging or informing the user about the reason for the failure
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
