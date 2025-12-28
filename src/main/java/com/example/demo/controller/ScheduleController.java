package com.example.demo.controller;
import com.example.demo.dto.ScheduleDto;
import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService service;
    public ScheduleController(ScheduleService service){this.service=service;}
    @PostMapping("/generate/{date}") public ResponseEntity<List<ScheduleDto>> generate(@PathVariable String date){ LocalDate d = LocalDate.parse(date); var gen = service.generateForDate(d); return ResponseEntity.ok(gen.stream().map(this::entityToDto).collect(Collectors.toList())); }
    @GetMapping("/date/{date}") public ResponseEntity<List<ScheduleDto>> byDate(@PathVariable String date){ LocalDate d = LocalDate.parse(date); var schedules = service.getByDate(d); return ResponseEntity.ok(schedules.stream().map(this::entityToDto).collect(Collectors.toList())); }
    private ScheduleDto entityToDto(GeneratedShiftSchedule g){ ScheduleDto dto = new ScheduleDto(); dto.setId(g.getId()); dto.setShiftDate(g.getShiftDate()); dto.setStartTime(g.getStartTime()); dto.setEndTime(g.getEndTime()); dto.setShiftTemplateId(g.getShiftTemplate()!=null?g.getShiftTemplate().getId():null); dto.setDepartmentId(g.getDepartment()!=null?g.getDepartment().getId():null); dto.setEmployeeId(g.getEmployee()!=null?g.getEmployee().getId():null); return dto; }
}
