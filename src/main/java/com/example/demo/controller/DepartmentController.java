package com.example.demo.controller;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService service;
    public DepartmentController(DepartmentService service){this.service=service;}
    @PostMapping public ResponseEntity<DepartmentDto> create(@Valid @RequestBody DepartmentDto dto){ Department d = dtoToEntity(dto); return ResponseEntity.ok(entityToDto(service.create(d))); }
    @PutMapping("/{id}") public ResponseEntity<DepartmentDto> update(@PathVariable Long id, @Valid @RequestBody DepartmentDto dto){ Department d = dtoToEntity(dto); return ResponseEntity.ok(entityToDto(service.update(id, d))); }
    @GetMapping("/{id}") public ResponseEntity<DepartmentDto> get(@PathVariable Long id){ return ResponseEntity.ok(entityToDto(service.get(id))); }
    @GetMapping public ResponseEntity<List<DepartmentDto>> list(){ return ResponseEntity.ok(service.getAll().stream().map(this::entityToDto).collect(Collectors.toList())); }
    @DeleteMapping("/{id}") public ResponseEntity<?> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.ok("Deleted"); }
    private Department dtoToEntity(DepartmentDto dto){ Department d = new Department(); BeanUtils.copyProperties(dto,d); return d; }
    private DepartmentDto entityToDto(Department d){ DepartmentDto dto = new DepartmentDto(); BeanUtils.copyProperties(d,dto); return dto; }
}
