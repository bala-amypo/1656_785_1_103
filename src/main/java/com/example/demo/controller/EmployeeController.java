package com.example.demo.controller;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;
    public EmployeeController(EmployeeService service){this.service=service;}
    @PostMapping public ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeDto dto){ Employee e = dtoToEntity(dto); Employee saved = service.createEmployee(e); return ResponseEntity.ok(entityToDto(saved)); }
    @PutMapping("/{id}") public ResponseEntity<EmployeeDto> update(@PathVariable Long id, @Valid @RequestBody EmployeeDto dto){ Employee e = dtoToEntity(dto); Employee updated = service.updateEmployee(id, e); return ResponseEntity.ok(entityToDto(updated)); }
    @GetMapping("/{id}") public ResponseEntity<EmployeeDto> get(@PathVariable Long id){ return ResponseEntity.ok(entityToDto(service.getEmployee(id))); }
    @GetMapping public ResponseEntity<List<EmployeeDto>> list(){ return ResponseEntity.ok(service.getAll().stream().map(this::entityToDto).collect(Collectors.toList())); }
    @DeleteMapping("/{id}") public ResponseEntity<?> delete(@PathVariable Long id){ service.deleteEmployee(id); return ResponseEntity.ok("Deleted"); }
    private Employee dtoToEntity(EmployeeDto dto){ Employee e = new Employee(); BeanUtils.copyProperties(dto,e); return e; }
    private EmployeeDto entityToDto(Employee e){ EmployeeDto d = new EmployeeDto(); BeanUtils.copyProperties(e,d); return d; }
}
