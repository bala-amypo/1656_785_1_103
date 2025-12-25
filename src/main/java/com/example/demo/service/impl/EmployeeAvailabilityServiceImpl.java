package com.example.demo.service.impl;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.EmployeeAvailabilityRepository;
import com.example.demo.service.EmployeeAvailabilityService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class EmployeeAvailabilityServiceImpl
        implements EmployeeAvailabilityService {

    private final EmployeeAvailabilityRepository repository;

    public EmployeeAvailabilityServiceImpl(
            EmployeeAvailabilityRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeAvailability addAvailability(EmployeeAvailability availability) {
        return repository.save(availability);
    }

    @Override
    public EmployeeAvailability updateAvailability(
            Long id, EmployeeAvailability availability) {
        EmployeeAvailability existing =
                repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("not found"));
        existing.setAvailable(availability.getAvailable());
        return repository.save(existing);
    }

    @Override
    public void deleteAvailability(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<EmployeeAvailability> getAvailabilityByDate(LocalDate date) {
        return repository.findByAvailableDateAndAvailable(date, true);
    }
}
