package com.example.demo.service.impl;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ShiftTemplateService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    private final ShiftTemplateRepository shiftTemplateRepository;

    public ShiftTemplateServiceImpl(ShiftTemplateRepository shiftTemplateRepository) {
        this.shiftTemplateRepository = shiftTemplateRepository;
    }

    @Override
    public ShiftTemplate createShiftTemplate(ShiftTemplate shiftTemplate) {
        if (!shiftTemplate.getEndTime().isAfter(shiftTemplate.getStartTime())) {
            throw new IllegalArgumentException("after");
        }

        shiftTemplateRepository
                .findByTemplateNameAndDepartment_Id(
                        shiftTemplate.getTemplateName(),
                        shiftTemplate.getDepartment().getId()
                ).ifPresent(t -> {
                    throw new IllegalArgumentException("exists");
                });

        return shiftTemplateRepository.save(shiftTemplate);
    }

    @Override
    public List<ShiftTemplate> getTemplatesByDepartment(Long departmentId) {
        return shiftTemplateRepository.findByDepartment_Id(departmentId);
    }
}
