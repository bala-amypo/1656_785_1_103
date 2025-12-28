package com.example.demo.service;

import java.util.List;
import com.example.demo.model.ShiftTemplate;

public interface ShiftTemplateService {

    ShiftTemplate create(ShiftTemplate template);

    List<ShiftTemplate> getByDepartment(Long departmentId);

    ShiftTemplate getTemplate(Long id);

    List<ShiftTemplate> getAll();
}
