package com.example.demo.dto;

public class EmployeeDto {

    private Long id;
    private String fullName;
    private String email;
    private String role;
    private String skills;
    private Integer maxWeeklyHours;

    public EmployeeDto() {}

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getSkills() {
        return skills;
    }

    public Integer getMaxWeeklyHours() {
        return maxWeeklyHours;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setMaxWeeklyHours(Integer maxWeeklyHours) {
        this.maxWeeklyHours = maxWeeklyHours;
    }
}