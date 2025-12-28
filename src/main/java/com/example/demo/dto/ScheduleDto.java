package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleDto {

    private Long id;
    private LocalDate shiftDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String employeeName;
    private String departmentName;

    public ScheduleDto() {}

    public Long getId() {
        return id;
    }

    public LocalDate getShiftDate() {
        return shiftDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setShiftDate(LocalDate shiftDate) {
        this.shiftDate = shiftDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
