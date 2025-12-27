package com.example.demo.dto;

public class EmployeeResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private Long departmentId;
    private String departmentName;

    public EmployeeResponseDTO() {}

    public EmployeeResponseDTO(Long id, String name, String email, String phone,
                               Long departmentId, String departmentName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
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
 
    public String getDepartmentName() {
        return departmentName;
    }
 
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
