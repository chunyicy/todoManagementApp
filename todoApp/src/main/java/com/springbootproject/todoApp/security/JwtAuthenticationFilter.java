package com.springbootproject.todoApp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;
    private UserDetailsService userDetailsService;

    // constructor injection of JwtTokenProvider and UserDetailsService, these dependencies
    // are necessary for validating JWT tokens and loading user details from the token
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
                                   UserDetailsService userDetailsService){
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;

    }

    // this method is called for each incoming request
    @Override
    protected void doFilterInternal(HttpServletRequest request, // incoming HTTP request
                                    HttpServletResponse response, // HTTP response
                                    // allows the request to proceed to the next filter in the chain
                                    FilterChain filterChain) throws ServletException, IOException {

        // retrieves the JWT token from the 'Authorization' header using 'getTokenFromRequest'
        String token = getTokenFromRequest(request);

        // validate the token using jwtTokenProvider.validateToken(token)
        if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)){

            // if the token is valid then extracts the username from the token
            String username = jwtTokenProvider.getUsername(token);
            // load user details from the database
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // constructs an Authentication object (UsernamePasswordAuthenticationToken)
            // with the retrieved UserDetails
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            // sets the authentication object into the SecurityContextHolder,
            // effectively authenticating the user for this request
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        // calls filterChain.doFilter(request, response) to pass the request and response to the
        // next filter in the chain
        filterChain.doFilter(request, response);
    }
    private String getTokenFromRequest(HttpServletRequest request){

        // retrieves the JWT token from the 'Authorization' header
        String bearerToken = request.getHeader("Authorization");

        // checks if the header value is not empty and starts with 'Bearer'
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){

            // returns the token part of the header, stripping off the 'Bearer' prefix
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }
}
