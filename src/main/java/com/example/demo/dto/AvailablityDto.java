package com.example.demo.dto;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
public class AvailabilityDto {
    private Long id;
    @NotNull private Long employeeId;
    @NotNull private LocalDate availableDate;
    @NotNull private Boolean available;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Long getEmployeeId(){return employeeId;} public void setEmployeeId(Long employeeId){this.employeeId=employeeId;}
    public LocalDate getAvailableDate(){return availableDate;} public void setAvailableDate(LocalDate availableDate){this.availableDate=availableDate;}
    public Boolean getAvailable(){return available;} public void setAvailable(Boolean available){this.available=available;}
}
