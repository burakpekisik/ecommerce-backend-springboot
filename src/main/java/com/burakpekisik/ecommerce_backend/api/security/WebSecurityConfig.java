package com.burakpekisik.ecommerce_backend.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private JWTRequestFilter jwtRequestFilter;

    public WebSecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(jwtRequestFilter, AuthenticationFilter.class)
                .securityMatcher((request) -> true) // Matches all requests
                .csrf(csrf -> csrf.disable()) // Disables CSRF
                .cors(cors -> cors.disable()) // Disables CORS
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/product", "/auth/register", "/auth/login", "/auth/verify", "/auth/forgot", "/auth/reset", "/error", "/websocket", "/websocket/**").permitAll()
                        .anyRequest().authenticated()); // Permits all requests
        return http.build();
    }
}
