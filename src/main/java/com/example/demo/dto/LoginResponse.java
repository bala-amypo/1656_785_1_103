package com.example.demo.dto;

public class LoginResponse {

    private String message;
    private String token;
    private Long userId;
    private String email;
    private String role;

    public LoginResponse(String message, String token, Long userId, String email, String role) {
        this.message = message;
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.role = role;
    }

    public String getMessage() { return message; }
    public String getToken() { return token; }
    public Long getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
}
