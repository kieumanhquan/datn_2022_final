package com.itsol.recruit.web.rest;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.WorkingForm;
import com.itsol.recruit.repository.WorkingFormRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class WorkingFormController {

    private final WorkingFormRepository workingFormRepository;

    public WorkingFormController(WorkingFormRepository workingFormRepository) {
        this.workingFormRepository = workingFormRepository;
    }

    @GetMapping("/workingForms")
    public List<WorkingForm> findAllWorkingForm() {
        return workingFormRepository.findAll();
    }
}
