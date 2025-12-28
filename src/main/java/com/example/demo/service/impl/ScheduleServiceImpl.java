package com.example.demo.service.impl;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    private final ShiftTemplateRepository shiftTemplateRepository;
    private final AvailabilityRepository availabilityRepository;
    private final EmployeeRepository employeeRepository;
    private final GeneratedShiftScheduleRepository scheduleRepo;
    private final DepartmentRepository departmentRepository;
    public ScheduleServiceImpl(ShiftTemplateRepository shiftTemplateRepository, AvailabilityRepository availabilityRepository, EmployeeRepository employeeRepository, GeneratedShiftScheduleRepository scheduleRepo, DepartmentRepository departmentRepository){
        this.shiftTemplateRepository=shiftTemplateRepository; this.availabilityRepository=availabilityRepository; this.employeeRepository=employeeRepository; this.scheduleRepo=scheduleRepo; this.departmentRepository=departmentRepository;
    }
    @Override public List<GeneratedShiftSchedule> generateForDate(LocalDate date){
        List<GeneratedShiftSchedule> results = new ArrayList<>();
        List<Department> departments = departmentRepository.findAll();
        for (Department dept: departments){
            List<ShiftTemplate> templates = shiftTemplateRepository.findByDepartment_Id(dept.getId());
            for (ShiftTemplate template: templates){
                List<EmployeeAvailability> availableEmployees = availabilityRepository.findByAvailableDateAndAvailable(date, true);
                List<Employee> candidates = availableEmployees.stream().map(EmployeeAvailability::getEmployee).filter(emp->empMatchesSkills(emp, template.getRequiredSkills(), dept.getRequiredSkills())).collect(Collectors.toList());
                Optional<Employee> chosen = candidates.stream().findFirst();
                if (chosen.isPresent()){
                    Employee emp = chosen.get();
                    GeneratedShiftSchedule schedule = new GeneratedShiftSchedule(date, template.getStartTime(), template.getEndTime(), template, dept, emp);
                    scheduleRepo.save(schedule);
                    results.add(schedule);
                }
            }
        }
        return results;
    }
    private boolean empMatchesSkills(Employee emp, String templateSkills, String deptSkills){
        Set<String> empSet = new HashSet<>();
        if (emp.getSkills()!=null) { Arrays.stream(emp.getSkills().split(",")).map(String::trim).forEach(s->empSet.add(s.toLowerCase())); }
        boolean templateOk = true; boolean deptOk = true;
        if (templateSkills!=null && !templateSkills.isBlank()) templateOk = Arrays.stream(templateSkills.split(",")).map(String::trim).allMatch(s->empSet.contains(s.toLowerCase()));
        if (deptSkills!=null && !deptSkills.isBlank()) deptOk = Arrays.stream(deptSkills.split(",")).map(String::trim).allMatch(s->empSet.contains(s.toLowerCase()));
        return templateOk && deptOk;
    }
    @Override public List<GeneratedShiftSchedule> getByDate(LocalDate date){ return scheduleRepo.findByShiftDate(date); }
}
