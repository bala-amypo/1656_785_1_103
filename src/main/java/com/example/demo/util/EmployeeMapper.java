package com.example.demo.util;

import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.model.Employee;
import com.example.demo.model.Department;

public class EmployeeMapper {

    // DTO → Entity
    public static Employee toEntity(EmployeeRequestDTO dto, Department department) {

        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setDepartment(department);

        return employee;
    }

    // Entity → DTO
    public static EmployeeResponseDTO toDTO(Employee employee) {

        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getDepartment().getId(),
                employee.getDepartment().getName()
        );
    }
}
