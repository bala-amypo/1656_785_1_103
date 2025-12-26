package com.example.demo.util;

import com.example.demo.dto.DepartmentRequestDTO;
import com.example.demo.dto.DepartmentResponseDTO;
import com.example.demo.model.Department;

public class DepartmentMapper {

    // DTO → Entity
    public static Department toEntity(DepartmentRequestDTO dto) {

        Department department = new Department();
        department.setName(dto.getName());
        return department;
    }

    // Entity → DTO
    public static DepartmentResponseDTO toDTO(Department department) {

        return new DepartmentResponseDTO(
                department.getId(),
                department.getName()
        );
    }
}
