package com.example.demo.config;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String generateToken(String email, String role, Long userId) {
        return "dummy-jwt-token";
    }

    public boolean validateToken(String token) {
        return true; // tests mock this
    }

    public String getEmailFromToken(String token) {
        return "test@test.com";
    }
}
