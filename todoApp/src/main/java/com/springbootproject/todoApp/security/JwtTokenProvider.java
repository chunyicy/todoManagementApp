package com.springbootproject.todoApp.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component // marks this class as a Spring component
public class JwtTokenProvider {
    // read the JWT secret key from application.properties file
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    // reads the JWT expiration time in milliseconds from application.properties file
    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    // generate a JWT token based on the 'Authentication' object provided
    public String generateToken(Authentication authentication){
        // get username from the authentication object so this will return username or email
        String username = authentication.getName();
        // calculate the token expiration based on the current time
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key()) // signs the token using the HMAC-SHA algorithm (signWith(key())),
                                // derived from the jwtSecret
                .compact();  // returns the compact representation of the token
        return  token;
    }
    // generates a 'key' object used for signing and verifying JWT tokens
    // uses Keys.hmacShaKeyFor to create a key from the base64-decoded jwtSecret
    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }


    // here extracts the username/email (subject) from a given JWT token
    public String getUsername(String token){
        // uses Jwts.parser() to parse and validate the token
        Claims claims = Jwts.parser().setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        // retrieves the subject (claims.getSubject()) from the token's body (getBody())
        String username = claims.getSubject();
        return username;
    }


    // validates whether a given JWT token is valid
    // attempts to parse the token using Jwts.parser()
    public boolean validateToken(String token){
        Jwts.parser()
                .setSigningKey(key())
                .build()
                .parse(token);

        // returns true if parsing succeeds without exceptions, indicating
        // a valid token; otherwise, returns false
        return true;
    }
}

