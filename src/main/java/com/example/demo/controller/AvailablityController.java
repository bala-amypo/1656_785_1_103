package com.example.demo.controller;
import com.example.demo.dto.AvailabilityDto;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AvailabilityService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {
    private final AvailabilityService service;
    private final EmployeeRepository employeeRepository;
    public AvailabilityController(AvailabilityService service, EmployeeRepository employeeRepository){this.service=service; this.employeeRepository=employeeRepository;}
    @PostMapping public ResponseEntity<AvailabilityDto> create(@Valid @RequestBody AvailabilityDto dto){ Employee emp = employeeRepository.findById(dto.getEmployeeId()).orElseThrow(()-> new IllegalArgumentException("Employee not found")); EmployeeAvailability av = new EmployeeAvailability(); av.setEmployee(emp); av.setAvailable(dto.getAvailable()); av.setAvailableDate(dto.getAvailableDate()); EmployeeAvailability saved = service.create(av); return ResponseEntity.ok(entityToDto(saved)); }
    @PutMapping("/{id}") public ResponseEntity<AvailabilityDto> update(@PathVariable Long id, @Valid @RequestBody AvailabilityDto dto){ EmployeeAvailability av = new EmployeeAvailability(); av.setAvailable(dto.getAvailable()); av.setAvailableDate(dto.getAvailableDate()); EmployeeAvailability updated = service.update(id, av); return ResponseEntity.ok(entityToDto(updated)); }
    @GetMapping("/date/{date}") public ResponseEntity<List<AvailabilityDto>> byDate(@PathVariable String date){ var list = service.getByDate(java.time.LocalDate.parse(date)); return ResponseEntity.ok(list.stream().map(this::entityToDto).collect(Collectors.toList())); }
    @DeleteMapping("/{id}") public ResponseEntity<?> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.ok("Deleted"); }
    private AvailabilityDto entityToDto(EmployeeAvailability av){ AvailabilityDto d=new AvailabilityDto(); d.setId(av.getId()); d.setEmployeeId(av.getEmployee().getId()); d.setAvailableDate(av.getAvailableDate()); d.setAvailable(av.getAvailable()); return d; }
}
