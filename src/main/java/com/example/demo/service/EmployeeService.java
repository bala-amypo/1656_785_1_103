package com.example.demo.service;
import com.example.demo.model.Employee;
import java.util.List;
public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee emp);
    Employee getEmployee(Long id);
    List<Employee> getAll();
    void deleteEmployee(Long id);
    Employee findByEmail(String email);
}
