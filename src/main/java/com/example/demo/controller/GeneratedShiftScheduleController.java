package com.example.demo.controller;

import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.service.GeneratedShiftScheduleService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class GeneratedShiftScheduleController {

    private final GeneratedShiftScheduleService scheduleService;

    public GeneratedShiftScheduleController(
            GeneratedShiftScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/generate/{date}")
    public List<GeneratedShiftSchedule> generateSchedule(
            @PathVariable String date) {
        return scheduleService.generateSchedule(
                LocalDate.parse(date));
    }

    @GetMapping("/date/{date}")
    public List<GeneratedShiftSchedule> getScheduleByDate(
            @PathVariable String date) {
        return scheduleService.getScheduleByDate(
                LocalDate.parse(date));
    }
}
