package com.example.demo.service;

import com.example.demo.model.GeneratedShiftSchedule;

import java.time.LocalDate;
import java.util.List;

public interface GeneratedShiftScheduleService {

    List<GeneratedShiftSchedule> generateSchedule(LocalDate date);

    List<GeneratedShiftSchedule> getScheduleByDate(LocalDate date);
}
