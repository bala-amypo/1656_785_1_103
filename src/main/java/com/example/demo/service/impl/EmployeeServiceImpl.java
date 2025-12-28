package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail()))
            throw new RuntimeException("exists");

        if (employee.getMaxWeeklyHours() == null || employee.getMaxWeeklyHours() <= 0)
            throw new RuntimeException("must");

        if (employee.getRole() == null)
            employee.setRole("STAFF");

        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public Employee updateEmployee(Long id, Employee updated) {
        Employee existing = getEmployee(id);

        if (updated.getEmail() != null &&
                !updated.getEmail().equals(existing.getEmail()) &&
                employeeRepository.existsByEmail(updated.getEmail()))
            throw new RuntimeException("exists");

        existing.setFullName(updated.getFullName());
        existing.setEmail(updated.getEmail());
        existing.setRole(updated.getRole());
        existing.setSkills(updated.getSkillsRaw());
        existing.setMaxWeeklyHours(updated.getMaxWeeklyHours());

        return employeeRepository.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee e = getEmployee(id);
        employeeRepository.delete(e);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("not found"));
    }
}
