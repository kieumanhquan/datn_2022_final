package com.itsol.recruit.web.rest;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.JobPosition;
import com.itsol.recruit.repository.JobPositionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class JobPositionController {
    private final JobPositionRepository jobPositionRepository;

    public JobPositionController(JobPositionRepository jobPositionRepository) {
        this.jobPositionRepository = jobPositionRepository;
    }

    @GetMapping("/jobPositions")
    public List<JobPosition> findAllJobPosition() {
        return jobPositionRepository.findAll();
    }
}
