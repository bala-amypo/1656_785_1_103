package com.example.demo.dto;

import java.time.LocalTime;

public class ShiftTemplateDto {

    private Long id;
    private String templateName;
    private LocalTime startTime;
    private LocalTime endTime;
    private String requiredSkills;

    public ShiftTemplateDto() {}

    public Long getId() {
        return id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
}
