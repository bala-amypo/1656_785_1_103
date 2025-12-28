// package com.example.demo.config;

// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;

// import java.security.Key;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;

// @Component
// public class JwtUtil {

//     private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

//     // === TEST CASE COMPATIBLE TOKEN GENERATOR ===
//     public String generateToken(Long userId, String email, String role) {
//         Map<String,Object> claims = new HashMap<>();
//         claims.put("userId", userId);
//         claims.put("email", email);
//         claims.put("role", role);

//         return Jwts.builder()
//                 .setClaims(claims)
//                 .setSubject(email)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + 3600000))
//                 .signWith(key)
//                 .compact();
//     }

//     // === USED BY TEST CASES ===
//     public Claims parseToken(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(key)
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody();
//     }

//     public boolean validateToken(String token) {
//         try {
//             parseToken(token);
//             return true;
//         } catch (Exception ex) {
//             return false;
//         }
//     }

//     public String getEmailFromToken(String token) {
//         return parseToken(token).get("email", String.class);
//     }
// }
package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "SECRET_KEY_123";
    private final long EXPIRATION = 24 * 60 * 60 * 1000;

    public String generateToken(Object user) {
        String subject = "";

        if (user instanceof com.example.demo.model.User u) {
            subject = u.getEmail();
        } else if (user instanceof String s) {
            subject = s;
        }

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return parseToken(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            parseToken(token); // ensures valid structure and signature
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // 🔥 REQUIRED BY JwtAuthenticationFilter
    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}

