// package com.example.demo.config;
// import com.example.demo.security.CustomUserDetailsService;
// import io.jsonwebtoken.Claims;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.*;
// import org.springframework.lang.NonNull;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.util.StringUtils;
// import org.springframework.web.filter.OncePerRequestFilter;
// import java.io.IOException;

// public class JwtAuthenticationFilter extends OncePerRequestFilter {
//     private final JwtUtil jwtUtil;
//     private final CustomUserDetailsService userDetailsService;
//     public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService uds){ this.jwtUtil=jwtUtil; this.userDetailsService=uds; }
//     @Override
//     protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain) throws ServletException, IOException {
//         String header = request.getHeader("Authorization");
//         if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
//             String token = header.substring(7);
//             if (jwtUtil.validateToken(token)) {
//                 Claims claims = jwtUtil.getClaimsFromToken(token);
//                 String email = claims.getSubject();
//                 if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                     UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//                     UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                     SecurityContextHolder.getContext().setAuthentication(auth);
//                 }
//             }
//         }
//         chain.doFilter(request, response);
//     }
// }
package com.example.demo.config;

import com.example.demo.security.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain chain
    ) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if (jwtUtil.validateToken(token)) {
                Claims claims = jwtUtil.parseToken(token);
                String email = claims.get("email", String.class);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails ud = userDetailsService.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }

        chain.doFilter(request, response);
    }
}
