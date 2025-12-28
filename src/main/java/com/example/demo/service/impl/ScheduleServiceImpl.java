package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ShiftTemplateRepository shiftTemplateRepository;
    private final AvailabilityRepository availabilityRepository;
    private final EmployeeRepository employeeRepository;
    private final GeneratedShiftScheduleRepository scheduleRepository;
    private final DepartmentRepository departmentRepository;

    public ScheduleServiceImpl(
            ShiftTemplateRepository shiftTemplateRepository,
            AvailabilityRepository availabilityRepository,
            EmployeeRepository employeeRepository,
            GeneratedShiftScheduleRepository scheduleRepository,
            DepartmentRepository departmentRepository) {
        this.shiftTemplateRepository = shiftTemplateRepository;
        this.availabilityRepository = availabilityRepository;
        this.employeeRepository = employeeRepository;
        this.scheduleRepository = scheduleRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {

        List<GeneratedShiftSchedule> result = new ArrayList<>();

        for (Department dept : departmentRepository.findAll()) {

            for (ShiftTemplate template :
                    shiftTemplateRepository.findByDepartment_Id(dept.getId())) {

                for (EmployeeAvailability av :
                        availabilityRepository.findByAvailableDateAndAvailable(date, true)) {

                    Employee emp = av.getEmployee();

                    if (emp.getSkills().containsAll(template.getRequiredSkillsSet())) {

                        GeneratedShiftSchedule g = new GeneratedShiftSchedule();
                        g.setShiftDate(date);
                        g.setStartTime(template.getStartTime());
                        g.setEndTime(template.getEndTime());
                        g.setDepartment(dept);
                        g.setEmployee(emp);
                        g.setShiftTemplate(template);

                        result.add(scheduleRepository.save(g));
                        break;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<GeneratedShiftSchedule> getByDate(LocalDate date) {
        return scheduleRepository.findByShiftDate(date);
    }
}
