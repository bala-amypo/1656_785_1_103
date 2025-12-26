package com.example.demo.service;

import com.example.demo.model.ShiftTemplate;

import java.util.List;

public interface ShiftTemplateService {

    ShiftTemplate createShiftTemplate(ShiftTemplate shiftTemplate);

    List<ShiftTemplate> getTemplatesByDepartment(Long departmentId);
}
