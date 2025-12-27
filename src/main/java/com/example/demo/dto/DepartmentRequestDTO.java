package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DepartmentRequestDTO {

    @NotBlank(message = "Department name is required")
    @Size(min = 3, max = 50, message = "Department name must be 3–50 characters")
    private String name;

    public DepartmentRequestDTO() {}

    public DepartmentRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
}
