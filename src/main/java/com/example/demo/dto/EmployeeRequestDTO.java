package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployeeRequestDTO {

    @NotBlank(message = "Employee name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 10, message = "Phone must be 10 digits")
    private String phone;

    @NotNull(message = "Department ID is required")
    private Long departmentId;

    public EmployeeRequestDTO() {}

    public EmployeeRequestDTO(String name, String email, String phone, Long departmentId) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPhone() {
        return phone;
    }
 
    public void setPhone(String phone) {
        this.phone = phone;
    }
 
    public Long getDepartmentId() {
        return departmentId;
    }
 
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
