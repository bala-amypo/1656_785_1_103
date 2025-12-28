package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
    name = "employee_availability",
    uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id","availableDate"})
)
public class EmployeeAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employee employee;

    private LocalDate availableDate;

    private Boolean available;

    public EmployeeAvailability() {}

    public EmployeeAvailability(Employee employee, LocalDate availableDate, Boolean available) {
        this.employee = employee;
        this.availableDate = availableDate;
        this.available = available;
    }

    public Long getId() { return id; }
    public Employee getEmployee() { return employee; }
    public LocalDate getAvailableDate() { return availableDate; }
    public Boolean getAvailable() { return available; }

    public void setId(Long id) { this.id = id; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public void setAvailableDate(LocalDate availableDate) { this.availableDate = availableDate; }
    public void setAvailable(Boolean available) { this.available = available; }
}
