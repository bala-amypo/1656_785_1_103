package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Department;
import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.ShiftTemplateService;

@RestController
@RequestMapping("/api/templates")
public class ShiftTemplateController {

    private final ShiftTemplateService shiftTemplateService;
    private final DepartmentRepository departmentRepository;

    public ShiftTemplateController(
            ShiftTemplateService shiftTemplateService,
            DepartmentRepository departmentRepository) {
        this.shiftTemplateService = shiftTemplateService;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public ResponseEntity<List<ShiftTemplate>> list() {
        return ResponseEntity.ok(shiftTemplateService.getAll());
    }

    @GetMapping("/department/{deptId}")
    public ResponseEntity<List<ShiftTemplate>> listByDepartment(
            @PathVariable Long deptId) {
        return ResponseEntity.ok(
                shiftTemplateService.getByDepartment(deptId));
    }

    @PostMapping("/department/{deptId}")
    public ResponseEntity<ShiftTemplate> create(
            @PathVariable Long deptId,
            @RequestBody ShiftTemplate template) {

        Department dept = departmentRepository.findById(deptId)
                .orElseThrow(() -> new RuntimeException("not found"));

        template.setDepartment(dept);
        return ResponseEntity.ok(
                shiftTemplateService.create(template));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftTemplate> get(@PathVariable Long id) {
        return ResponseEntity.ok(
                shiftTemplateService.getTemplate(id));
    }
}
