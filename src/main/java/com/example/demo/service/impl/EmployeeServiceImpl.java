package com.example.demo.service.impl;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repo;
    public EmployeeServiceImpl(EmployeeRepository repo){this.repo=repo;}
    @Override public Employee createEmployee(Employee employee){
        if (repo.existsByEmail(employee.getEmail())) throw new IllegalArgumentException("Email already exists");
        if (employee.getMaxWeeklyHours()==null || employee.getMaxWeeklyHours()<=0) throw new IllegalArgumentException("maxWeeklyHours must be > 0");
        if (employee.getRole()==null) employee.setRole("STAFF");
        return repo.save(employee);
    }
    @Override public Employee updateEmployee(Long id, Employee emp){
        Employee existing = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
        if (!existing.getEmail().equals(emp.getEmail()) && repo.existsByEmail(emp.getEmail())) throw new IllegalArgumentException("Email already exists");
        existing.setFullName(emp.getFullName()); existing.setEmail(emp.getEmail()); existing.setRole(emp.getRole()); existing.setSkills(emp.getSkills()); existing.setMaxWeeklyHours(emp.getMaxWeeklyHours());
        return repo.save(existing);
    }
    @Override public Employee getEmployee(Long id){ return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found")); }
    @Override public List<Employee> getAll(){ return repo.findAll(); }
    @Override public void deleteEmployee(Long id){ Employee e=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found")); repo.delete(e); }
    @Override public Employee findByEmail(String email){ return repo.findByEmail(email).orElse(null); }
}
