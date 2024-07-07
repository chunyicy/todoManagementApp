package com.springbootproject.todoApp.config;


import com.springbootproject.todoApp.security.JwtAuthenticationEntryPoint;
import com.springbootproject.todoApp.security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.websocket.servlet.UndertowWebSocketServletWebServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableMethodSecurity
public class SpringSecurityConfig {

    // it will load the user and role from the database
    private UserDetailsService userDetailsService;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtAuthenticationFilter authenticationFilter;
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // all the incoming Http request is authenticated by using anyRequest().authenticated() method
    // configures the security filter chain using HttpSecurity builder
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(AbstractHttpConfigurer::disable) // disables CSRF protection, not using CSRF method()
                // to authorize all the incoming HTTP request
                .authorizeHttpRequests((authorize) ->{
                    // allow access to endpoints under '/api/auth/**' without authentication
                    authorize.requestMatchers("/api/auth/**").permitAll();
                    // requires authentication for any other requests
                    // all the incoming HTTP request is authenticated by using this method
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults()); // configure basic authentication

        // configures exception handling to use jwtAuthenticationEntryPoint for authentication errors
        http.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint));

        // adds authenticationFilter before UsernamePasswordAuthenticationFilter to handle JWT authentication
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // retrieves the authentication manager from Spring's configuration
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration
                                                                   configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }


}
