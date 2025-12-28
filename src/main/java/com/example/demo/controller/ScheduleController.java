package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.service.ScheduleService;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/generate/{date}")
    public ResponseEntity<List<GeneratedShiftSchedule>> generate(
            @PathVariable String date) {

        return ResponseEntity.ok(
                scheduleService.generateForDate(LocalDate.parse(date)));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<GeneratedShiftSchedule>> byDate(
            @PathVariable String date) {

        return ResponseEntity.ok(
                scheduleService.getByDate(LocalDate.parse(date)));
    }
}
