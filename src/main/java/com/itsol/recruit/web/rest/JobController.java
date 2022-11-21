package com.itsol.recruit.web.rest;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.dto.JobPaginationDto;
import com.itsol.recruit.dto.ReasonDto;
import com.itsol.recruit.dto.StatusDto;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.service.JobService;
import com.itsol.recruit.web.vm.DashboardVM;
import com.itsol.recruit.web.vm.SearchJobVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC + "/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/searches")
    public JobPaginationDto find(@RequestBody SearchJobVM searchJobVM, @RequestParam(name = "page") int pageNumber,
                                 @RequestParam(name = "size") int pageSize) {
        return jobService.find(searchJobVM, pageNumber, pageSize);
    }

    @PostMapping("/searches/sortByName")
    public JobPaginationDto sortByName(@RequestBody SearchJobVM searchJobVM, @RequestParam(name = "page") int pageNumber,
                                       @RequestParam(name = "size") int pageSize) {
        return jobService.sortByName(searchJobVM, pageNumber, pageSize);
    }

    @PostMapping("/find-total-job")
    public ResponseEntity<Long> getTotalJob(@RequestBody DashboardVM dashboardVM) {
        return ResponseEntity.ok().body(jobService.getTotalJob(dashboardVM.getStartDate(),
                dashboardVM.getEndDate()));
    }

    @PostMapping("/find-total-views")
    public ResponseEntity<Long> getTotalViews(@RequestBody DashboardVM dashboardVM) {
        return ResponseEntity.ok().body(jobService.getTotalViews(dashboardVM.getStartDate(),
                dashboardVM.getEndDate()));
    }

    @PostMapping("/find-total-dueDate")
    public ResponseEntity<Long> getTotalDueDate(@RequestBody DashboardVM dashboardVM) {
        return ResponseEntity.ok().body(jobService.getTotalJobDueDate(dashboardVM.getStartDate(),
                dashboardVM.getEndDate()));
    }

    @GetMapping()
    public List<Job> findAll() {
        return jobService.findAll();
    }

    @PostMapping
    public ResponseEntity<Job> add(@Valid @RequestBody JobDTO jobDTO) {
        return ResponseEntity.ok().body(jobService.add(jobDTO));
    }

    @PutMapping
    public ResponseEntity<Job> update(@Valid @RequestBody JobDTO jobDTO) {
        return ResponseEntity.ok().body(jobService.add(jobDTO));
    }

    @GetMapping("/id={id}")
    public ResponseEntity<Job> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(jobService.getById(id));
    }


    @GetMapping("/news")
    public ResponseEntity<JobPaginationDto> getNewJob(@RequestParam(name = "numberDay") Integer numberDay,
                                                      @RequestParam(name = "page") int pageNumber,
                                                      @RequestParam(name = "size") int pageSize) {
        return ResponseEntity.ok().body(jobService.getNewJob(numberDay, pageNumber, pageSize));
    }

    @GetMapping("/salary-highs")
    public ResponseEntity<JobPaginationDto> getHigghSalary(@RequestParam(name = "salary") Integer salary,
                                                           @RequestParam(name = "page") int pageNumber,
                                                           @RequestParam(name = "size") int pageSize) {
        return ResponseEntity.ok().body(jobService.getJobHighSalary(salary, pageNumber, pageSize));
    }

    @GetMapping("/due-dates")
    public ResponseEntity<JobPaginationDto> getJobDueDate(@RequestParam(name = "numberDay") Integer numberDay,
                                                          @RequestParam(name = "page") int pageNumber,
                                                          @RequestParam(name = "size") int pageSize) {
        return ResponseEntity.ok().body(jobService.getJobDue(numberDay, pageNumber, pageSize));
    }

    @PutMapping("/status_job")
    public ResponseEntity<Job> updateStatusJob(@RequestBody StatusDto statusDto) {
        return ResponseEntity.ok().body(jobService.updateStatus(statusDto));
    }

    @PutMapping("/reason")
    public ResponseEntity<Job> updateReason(@RequestBody ReasonDto reasonDto) {
        return ResponseEntity.ok().body(jobService.updateReason(reasonDto));
    }

    @DeleteMapping("/id={id}")
    public void delete(@PathVariable("id") Long id) {
        jobService.delete(id);
    }

    @GetMapping("/views/id={id}")
    public void addViews(@PathVariable("id") Long id) {
        jobService.addView(id);
    }
}
