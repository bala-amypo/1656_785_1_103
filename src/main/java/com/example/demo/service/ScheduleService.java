package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import com.example.demo.model.GeneratedShiftSchedule;

public interface ScheduleService {

    List<GeneratedShiftSchedule> generateForDate(LocalDate date);

    List<GeneratedShiftSchedule> getByDate(LocalDate date);
}
