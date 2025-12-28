package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "generated_shift_schedules")
public class GeneratedShiftSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate shiftDate;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    private ShiftTemplate shiftTemplate;

    @ManyToOne
    private Department department;

    @ManyToOne
    private Employee employee;

    public GeneratedShiftSchedule() {}

    public Long getId() { return id; }
    public LocalDate getShiftDate() { return shiftDate; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public ShiftTemplate getShiftTemplate() { return shiftTemplate; }
    public Department getDepartment() { return department; }
    public Employee getEmployee() { return employee; }

    public void setId(Long id) { this.id = id; }
    public void setShiftDate(LocalDate shiftDate) { this.shiftDate = shiftDate; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public void setShiftTemplate(ShiftTemplate shiftTemplate) { this.shiftTemplate = shiftTemplate; }
    public void setDepartment(Department department) { this.department = department; }
    public void setEmployee(Employee employee) { this.employee = employee; }
}
    