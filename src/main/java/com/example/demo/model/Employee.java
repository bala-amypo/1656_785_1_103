package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String role;

    private String skills;

    private Integer maxWeeklyHours;

    private LocalDateTime createdAt;

    public Employee() {}

    public Employee(String fullName, String email, String role, String skills, Integer maxWeeklyHours) {
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.skills = skills;
        this.maxWeeklyHours = maxWeeklyHours;
    }

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        if (role == null) role = "STAFF";
    }

    public Set<String> getSkills() {
        if (skills == null) return Set.of();
        return Set.of(skills.split(","));
    }

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getSkillsRaw() { return skills; }
    public Integer getMaxWeeklyHours() { return maxWeeklyHours; }

    public void setId(Long id) { this.id = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
    public void setSkills(String skills) { this.skills = skills; }
    public void setMaxWeeklyHours(Integer maxWeeklyHours) { this.maxWeeklyHours = maxWeeklyHours; }
}
