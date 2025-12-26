package com.example.demo.service;

import com.example.demo.model.EmployeeAvailability;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeAvailabilityService {

    EmployeeAvailability addAvailability(EmployeeAvailability availability);

    EmployeeAvailability updateAvailability(Long id, EmployeeAvailability availability);

    void deleteAvailability(Long id);

    List<EmployeeAvailability> getAvailabilityByDate(LocalDate date);
}
