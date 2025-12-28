package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    private String requiredSkills;

    private LocalDateTime createdAt;

    public Department() {}

    public Department(String name, String description, String requiredSkills) {
        this.name = name;
        this.description = description;
        this.requiredSkills = requiredSkills;
    }

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Set<String> getRequiredSkills() {
        if (requiredSkills == null) return Set.of();
        return Set.of(requiredSkills.split(","));
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getRequiredSkillsRaw() { return requiredSkills; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setRequiredSkills(String requiredSkills) { this.requiredSkills = requiredSkills; }
}
