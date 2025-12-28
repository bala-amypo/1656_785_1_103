package com.example.demo.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
public class EmployeeDto {
    private Long id;
    @NotBlank private String fullName;
    @Email @NotBlank private String email;
    private String role;
    private String skills;
    @Min(1) private Integer maxWeeklyHours;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getFullName(){return fullName;} public void setFullName(String fullName){this.fullName=fullName;}
    public String getEmail(){return email;} public void setEmail(String email){this.email=email;}
    public String getRole(){return role;} public void setRole(String role){this.role=role;}
    public String getSkills(){return skills;} public void setSkills(String skills){this.skills=skills;}
    public Integer getMaxWeeklyHours(){return maxWeeklyHours;} public void setMaxWeeklyHours(Integer maxWeeklyHours){this.maxWeeklyHours=maxWeeklyHours;}
}
