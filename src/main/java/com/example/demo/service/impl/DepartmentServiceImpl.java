package com.example.demo.service.impl;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository repo;
    public DepartmentServiceImpl(DepartmentRepository repo){this.repo=repo;}
    @Override public Department create(Department d){ if (repo.existsByName(d.getName())) throw new IllegalArgumentException("Department name exists"); return repo.save(d); }
    @Override public Department update(Long id, Department d){ Department existing=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department not found")); if (!existing.getName().equals(d.getName()) && repo.existsByName(d.getName())) throw new IllegalArgumentException("Department name exists"); existing.setName(d.getName()); existing.setDescription(d.getDescription()); existing.setRequiredSkills(d.getRequiredSkills()); return repo.save(existing); }
    @Override public Department get(Long id){ return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department not found")); }
    @Override public List<Department> getAll(){ return repo.findAll(); }
    @Override public void delete(Long id){ Department d=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department not found")); repo.delete(d); }
}
