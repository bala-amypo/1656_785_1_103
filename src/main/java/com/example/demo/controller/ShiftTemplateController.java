package com.example.demo.controller;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shift-templates")
public class ShiftTemplateController {

    private final ShiftTemplateService shiftTemplateService;

    public ShiftTemplateController(ShiftTemplateService shiftTemplateService) {
        this.shiftTemplateService = shiftTemplateService;
    }

    @PostMapping("/create")
    public ShiftTemplate createTemplate(@RequestBody ShiftTemplate template) {
        return shiftTemplateService.createShiftTemplate(template);
    }

    @GetMapping("/department/{departmentId}")
    public List<ShiftTemplate> getTemplatesByDepartment(
            @PathVariable Long departmentId) {
        return shiftTemplateService.getTemplatesByDepartment(departmentId);
    }
}
