package com.example.demo.service;
import com.example.demo.model.ShiftTemplate;
import java.util.List;
public interface ShiftTemplateService {
    ShiftTemplate create(ShiftTemplate st);
    ShiftTemplate update(Long id, ShiftTemplate st);
    ShiftTemplate get(Long id);
    List<ShiftTemplate> getAll();
    void delete(Long id);
    List<ShiftTemplate> getByDepartment(Long deptId);
}
