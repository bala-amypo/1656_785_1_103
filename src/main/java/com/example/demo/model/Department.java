package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "departments", uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
public class Department {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank private String name;

    @Column(columnDefinition = "TEXT") private String description;

    @Column(columnDefinition = "TEXT") private String requiredSkills;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Department() {}
    public Department(String name, String description, String requiredSkills){this.name=name;this.description=description;this.requiredSkills=requiredSkills;}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getDescription(){return description;} public void setDescription(String description){this.description=description;}
    public String getRequiredSkills(){return requiredSkills;} public void setRequiredSkills(String requiredSkills){this.requiredSkills=requiredSkills;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
}
