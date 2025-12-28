package com.example.demo.dto;
import jakarta.validation.constraints.NotBlank;
public class DepartmentDto {
    private Long id;
    @NotBlank private String name;
    private String description;
    private String requiredSkills;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getDescription(){return description;} public void setDescription(String description){this.description=description;}
    public String getRequiredSkills(){return requiredSkills;} public void setRequiredSkills(String requiredSkills){this.requiredSkills=requiredSkills;}
}
