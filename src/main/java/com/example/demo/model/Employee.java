package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "employees", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank private String fullName;

    @Email @NotBlank private String email;

    @NotBlank private String role = "STAFF";

    @Column(columnDefinition = "TEXT") private String skills;

    @Min(1) private Integer maxWeeklyHours;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Employee() {}

    public Employee(String fullName, String email, String role, String skills, Integer maxWeeklyHours) {
        this.fullName = fullName; this.email = email; if (role!=null) this.role = role; this.skills = skills; this.maxWeeklyHours = maxWeeklyHours;
    }

    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getFullName(){return fullName;} public void setFullName(String fullName){this.fullName=fullName;}
    public String getEmail(){return email;} public void setEmail(String email){this.email=email;}
    public String getRole(){return role;} public void setRole(String role){this.role=role;}
    public String getSkills(){return skills;} public void setSkills(String skills){this.skills=skills;}
    public Integer getMaxWeeklyHours(){return maxWeeklyHours;} public void setMaxWeeklyHours(Integer maxWeeklyHours){this.maxWeeklyHours=maxWeeklyHours;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
}
