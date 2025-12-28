package com.example.demo.service;
import com.example.demo.model.Department;
import java.util.List;
public interface DepartmentService {
    Department create(Department d);
    Department update(Long id, Department d);
    Department get(Long id);
    List<Department> getAll();
    void delete(Long id);
}
