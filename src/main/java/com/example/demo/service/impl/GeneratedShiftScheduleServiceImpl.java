package com.example.demo.service.impl;

import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.repository.GeneratedShiftScheduleRepository;
import com.example.demo.service.GeneratedShiftScheduleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class GeneratedShiftScheduleServiceImpl
        implements GeneratedShiftScheduleService {

    private final GeneratedShiftScheduleRepository repository;

    public GeneratedShiftScheduleServiceImpl(
            GeneratedShiftScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GeneratedShiftSchedule> generateSchedule(LocalDate date) {
        // Placeholder for scheduling logic
        return repository.findByShiftDate(date);
    }

    @Override
    public List<GeneratedShiftSchedule> getScheduleByDate(LocalDate date) {
        return repository.findByShiftDate(date);
    }
}
