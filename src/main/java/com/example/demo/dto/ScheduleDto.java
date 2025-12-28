package com.example.demo.dto;
import java.time.LocalDate;
import java.time.LocalTime;
public class ScheduleDto {
    private Long id; private LocalDate shiftDate; private LocalTime startTime; private LocalTime endTime;
    private Long shiftTemplateId; private Long departmentId; private Long employeeId;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public LocalDate getShiftDate(){return shiftDate;} public void setShiftDate(LocalDate shiftDate){this.shiftDate=shiftDate;}
    public LocalTime getStartTime(){return startTime;} public void setStartTime(LocalTime startTime){this.startTime=startTime;}
    public LocalTime getEndTime(){return endTime;} public void setEndTime(LocalTime endTime){this.endTime=endTime;}
    public Long getShiftTemplateId(){return shiftTemplateId;} public void setShiftTemplateId(Long shiftTemplateId){this.shiftTemplateId=shiftTemplateId;}
    public Long getDepartmentId(){return departmentId;} public void setDepartmentId(Long departmentId){this.departmentId=departmentId;}
    public Long getEmployeeId(){return employeeId;} public void setEmployeeId(Long employeeId){this.employeeId=employeeId;}
}
