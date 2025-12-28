package com.example.demo.controller;
import com.example.demo.dto.ShiftTemplateDto;
import com.example.demo.model.Department;
import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.ShiftTemplateService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/shift-templates")
public class ShiftTemplateController {
    private final ShiftTemplateService service;
    private final DepartmentRepository departmentRepository;
    public ShiftTemplateController(ShiftTemplateService service, DepartmentRepository departmentRepository){this.service=service; this.departmentRepository=departmentRepository;}
    @PostMapping public ResponseEntity<ShiftTemplateDto> create(@Valid @RequestBody ShiftTemplateDto dto){ Department dept = departmentRepository.findById(dto.getDepartmentId()).orElseThrow(()-> new IllegalArgumentException("Department not found")); ShiftTemplate st = dtoToEntity(dto); st.setDepartment(dept); return ResponseEntity.ok(entityToDto(service.create(st))); }
    @PutMapping("/{id}") public ResponseEntity<ShiftTemplateDto> update(@PathVariable Long id, @Valid @RequestBody ShiftTemplateDto dto){ ShiftTemplate st = dtoToEntity(dto); return ResponseEntity.ok(entityToDto(service.update(id, st))); }
    @GetMapping("/{id}") public ResponseEntity<ShiftTemplateDto> get(@PathVariable Long id){ return ResponseEntity.ok(entityToDto(service.get(id))); }
    @GetMapping public ResponseEntity<List<ShiftTemplateDto>> list(){ return ResponseEntity.ok(service.getAll().stream().map(this::entityToDto).collect(Collectors.toList())); }
    @DeleteMapping("/{id}") public ResponseEntity<?> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.ok("Deleted"); }
    private ShiftTemplate dtoToEntity(ShiftTemplateDto dto){ ShiftTemplate st = new ShiftTemplate(); BeanUtils.copyProperties(dto, st); return st; }
    private ShiftTemplateDto entityToDto(ShiftTemplate st){ ShiftTemplateDto dto = new ShiftTemplateDto(); dto.setId(st.getId()); dto.setTemplateName(st.getTemplateName()); dto.setStartTime(st.getStartTime()); dto.setEndTime(st.getEndTime()); dto.setRequiredSkills(st.getRequiredSkills()); dto.setDepartmentId(st.getDepartment()!=null?st.getDepartment().getId():null); return dto; }
}
