package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AvailabilityDto;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AvailabilityService;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;
    private final EmployeeRepository employeeRepository;

    public AvailabilityController(AvailabilityService availabilityService,
                                  EmployeeRepository employeeRepository) {
        this.availabilityService = availabilityService;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/{employeeId}")
    public ResponseEntity<EmployeeAvailability> create(
            @PathVariable Long employeeId,
            @RequestBody AvailabilityDto dto) {

        Employee emp = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("not found"));

        EmployeeAvailability av =
                new EmployeeAvailability(emp, dto.getAvailableDate(), dto.getAvailable());

        return ResponseEntity.ok(availabilityService.create(av));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<EmployeeAvailability>> byDate(
            @PathVariable String date) {

        return ResponseEntity.ok(
                availabilityService.getByDate(LocalDate.parse(date)));
    }
}
