package com.example.demo.config;

import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final String SECRET =
            "sdjhgbwubwwbgwiub8QFQ8qg87G1bfewifbiuwg7iu8wefqhjk";

    private final SecretKey key;
    private int expirationMs = 10 * 60 * 1000;

    public JwtUtil() {
        this.key = Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public JwtUtil(String secret, int expirationMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationMs;
    }

    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
                .claim("userId", userId)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // -----------------------------
    // NEW: keep this for app logic
    // -----------------------------
    public Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    // ---------------------------------------------------
    // IMPORTANT: this signature MUST match what tests use
    // ---------------------------------------------------
    public boolean validateToken(String token) {
        try {
            parseToken(token);   // if parsing fails → exception
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims extractAllClaims(String token) {
        return parseToken(token).getBody();
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).get("email", String.class);
    }

    // ---------------------------------------------------
    // IMPORTANT: tests call this exact method name
    // ---------------------------------------------------
    public String getEmailFromToken(String token) {
        return extractEmail(token);
    }

    public ResponseEntity<Boolean> isTokenValid(String token) {
        return ResponseEntity.ok(validateToken(token));
    }

    public ResponseEntity<Boolean> isTokenValid(String token, String email) {
        try {
            String tokenEmail = extractEmail(token);
            boolean valid = tokenEmail.equals(email) && !isTokenExpired(token);
            return ResponseEntity.ok(valid);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }
}
