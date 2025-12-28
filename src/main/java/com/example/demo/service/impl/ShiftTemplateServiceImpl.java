package com.example.demo.service.impl;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@Transactional
public class ShiftTemplateServiceImpl implements ShiftTemplateService {
    private final ShiftTemplateRepository repo;
    private final DepartmentRepository departmentRepository;
    public ShiftTemplateServiceImpl(ShiftTemplateRepository repo, DepartmentRepository departmentRepository){this.repo=repo; this.departmentRepository=departmentRepository;}
    @Override public ShiftTemplate create(ShiftTemplate st){
        Department dept = departmentRepository.findById(st.getDepartment().getId()).orElseThrow(()-> new ResourceNotFoundException("Department not found"));
        if (st.getEndTime().isBefore(st.getStartTime()) || st.getEndTime().equals(st.getStartTime())) throw new IllegalArgumentException("endTime must be after startTime");
        if (repo.findByTemplateNameAndDepartment_Id(st.getTemplateName(), dept.getId()).isPresent()) throw new IllegalArgumentException("Template name must be unique within department");
        st.setDepartment(dept);
        return repo.save(st);
    }
    @Override public ShiftTemplate update(Long id, ShiftTemplate st){ ShiftTemplate existing=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("ShiftTemplate not found")); if (st.getEndTime().isBefore(st.getStartTime()) || st.getEndTime().equals(st.getStartTime())) throw new IllegalArgumentException("endTime must be after startTime"); existing.setTemplateName(st.getTemplateName()); existing.setStartTime(st.getStartTime()); existing.setEndTime(st.getEndTime()); existing.setRequiredSkills(st.getRequiredSkills()); return repo.save(existing); }
    @Override public ShiftTemplate get(Long id){ return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("ShiftTemplate not found")); }
    @Override public List<ShiftTemplate> getAll(){ return repo.findAll(); }
    @Override public void delete(Long id){ ShiftTemplate st=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("ShiftTemplate not found")); repo.delete(st); }
    @Override public List<ShiftTemplate> getByDepartment(Long deptId){ return repo.findByDepartment_Id(deptId); }
}
