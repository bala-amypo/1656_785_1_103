package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Department;

public interface DepartmentService {

    Department create(Department department);

    Department get(Long id);

    void delete(Long id);

    List<Department> getAll();
}
