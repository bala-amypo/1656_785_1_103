package com.example.demo.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
public class ShiftTemplateDto {
    private Long id;
    @NotBlank private String templateName;
    @NotNull private LocalTime startTime;
    @NotNull private LocalTime endTime;
    private String requiredSkills;
    @NotNull private Long departmentId;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getTemplateName(){return templateName;} public void setTemplateName(String templateName){this.templateName=templateName;}
    public LocalTime getStartTime(){return startTime;} public void setStartTime(LocalTime startTime){this.startTime=startTime;}
    public LocalTime getEndTime(){return endTime;} public void setEndTime(LocalTime endTime){this.endTime=endTime;}
    public String getRequiredSkills(){return requiredSkills;} public void setRequiredSkills(String requiredSkills){this.requiredSkills=requiredSkills;}
    public Long getDepartmentId(){return departmentId;} public void setDepartmentId(Long departmentId){this.departmentId=departmentId;}
}
