package com.example.demo.controller;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.service.EmployeeAvailabilityService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class EmployeeAvailabilityController {

    private final EmployeeAvailabilityService availabilityService;

    public EmployeeAvailabilityController(
            EmployeeAvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @PostMapping("/add")
    public EmployeeAvailability addAvailability(
            @RequestBody EmployeeAvailability availability) {
        return availabilityService.addAvailability(availability);
    }

    @PutMapping("/{id}")
    public EmployeeAvailability updateAvailability(
            @PathVariable Long id,
            @RequestBody EmployeeAvailability availability) {
        return availabilityService.updateAvailability(id, availability);
    }

    @DeleteMapping("/{id}")
    public String deleteAvailability(@PathVariable Long id) {
        availabilityService.deleteAvailability(id);
        return "Availability deleted successfully";
    }

    @GetMapping("/date/{date}")
    public List<EmployeeAvailability> getAvailabilityByDate(
            @PathVariable String date) {
        return availabilityService.getAvailabilityByDate(
                LocalDate.parse(date));
    }
}
