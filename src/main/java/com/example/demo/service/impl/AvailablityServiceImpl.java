package com.example.demo.service.impl;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.AvailabilityRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
@Service
@Transactional
public class AvailabilityServiceImpl implements AvailabilityService {
    private final AvailabilityRepository repo;
    private final EmployeeRepository employeeRepository;
    public AvailabilityServiceImpl(AvailabilityRepository repo, EmployeeRepository employeeRepository){this.repo=repo; this.employeeRepository=employeeRepository;}
    @Override public EmployeeAvailability create(EmployeeAvailability av){
        Employee emp = employeeRepository.findById(av.getEmployee().getId()).orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
        if (repo.findByEmployee_IdAndAvailableDate(emp.getId(), av.getAvailableDate()).isPresent()) throw new IllegalArgumentException("Availability already exists for employee and date");
        av.setEmployee(emp);
        return repo.save(av);
    }
    @Override public EmployeeAvailability update(Long id, EmployeeAvailability av){ EmployeeAvailability existing = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Availability not found")); existing.setAvailable(av.getAvailable()); existing.setAvailableDate(av.getAvailableDate()); return repo.save(existing); }
    @Override public EmployeeAvailability get(Long id){ return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Availability not found")); }
    @Override public List<EmployeeAvailability> getByDate(LocalDate date){ return repo.findByAvailableDateAndAvailable(date, true); }
    @Override public void delete(Long id){ EmployeeAvailability existing=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Availability not found")); repo.delete(existing); }
}
