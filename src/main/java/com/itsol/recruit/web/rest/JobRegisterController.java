package com.itsol.recruit.web.rest;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.JobRegisterPaginationDto;
import com.itsol.recruit.dto.ReasonDto;
import com.itsol.recruit.dto.ScheduleDto;
import com.itsol.recruit.dto.StatusRegisterDto;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.service.JobRegisterService;
import com.itsol.recruit.web.vm.DashboardVM;
import com.itsol.recruit.web.vm.SearchJobRegisterVM;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC + "/job-registers")
public class JobRegisterController {

    private final JobRegisterService jobRegisterService;

    public JobRegisterController(JobRegisterService jobRegisterService) {
        this.jobRegisterService = jobRegisterService;
    }

    @PostMapping("/searches")
    public JobRegisterPaginationDto find(@RequestBody SearchJobRegisterVM searchJobRegisterVM, @RequestParam(name = "page") int pageNumber, @RequestParam(name = "size") int pageSize) {
        return jobRegisterService.find(searchJobRegisterVM, pageNumber, pageSize);
    }

    @PostMapping("/searches/sortByName")
    public JobRegisterPaginationDto sortByName(@RequestBody SearchJobRegisterVM searchJobRegisterVM, @RequestParam(name = "page") int pageNumber, @RequestParam(name = "size") int pageSize) {
        return jobRegisterService.sortByName(searchJobRegisterVM, pageNumber, pageSize);
    }

    @PutMapping()
    public ResponseEntity<JobRegister> schedule(@RequestBody JobRegister jobRegister) {
        return ResponseEntity.ok().body(jobRegisterService.save(jobRegister));
    }

    @GetMapping("/id={id}")
    public ResponseEntity<JobRegister> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(jobRegisterService.getById(id));
    }

    @PostMapping("/find-by-date-and-status")
    public ResponseEntity<Long> getByDateAndStatus(@RequestBody DashboardVM dashboardVM) {
        return ResponseEntity.ok().body(jobRegisterService.getByDateAndStatus(dashboardVM.getStartDate(),
                dashboardVM.getEndDate(), dashboardVM.getStatusId()));
    }

    @PostMapping("/find-total-register")
    public ResponseEntity<Long> getTotalRegister(@RequestBody DashboardVM dashboardVM) {
        return ResponseEntity.ok().body(jobRegisterService.getTotalRegister(dashboardVM.getStartDate(),
                dashboardVM.getEndDate()));
    }

    @PostMapping("/find-total-by-month")
    public ResponseEntity<List<Long>> getTotalByMonth(@RequestBody DashboardVM dashboardVM) {
        return ResponseEntity.ok().body(jobRegisterService.getTotalByMonth(dashboardVM.getStartDate(),
                dashboardVM.getEndDate(),dashboardVM.getStatusId()));
    }


    @GetMapping("/jobId={id}")
    public ResponseEntity<List<JobRegister>> getByJobId(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(jobRegisterService.getByJobId(id));
    }

    @PutMapping("/status_job")
    public ResponseEntity<JobRegister> updateStatusJob(@RequestBody StatusRegisterDto statusRegisterDto) {
        return ResponseEntity.ok().body(jobRegisterService.updateStatus(statusRegisterDto));
    }

    @PutMapping("/schedule")
    public ResponseEntity<JobRegister> schedule(@RequestBody ScheduleDto scheduleDto) {
        return ResponseEntity.ok().body(jobRegisterService.schedule(scheduleDto));
    }

    @PutMapping("/reason")
    public ResponseEntity<JobRegister> updateReason(@RequestBody ReasonDto reasonDto) {
        return ResponseEntity.ok().body(jobRegisterService.updateReason(reasonDto));
    }


    @GetMapping("/download/{id}")
    @CrossOrigin
    public ResponseEntity<Resource> downloadApplicantCv(@PathVariable("id") Long id) throws Exception {
        Resource resource = jobRegisterService.downloadCv(id);
        Path path = resource.getFile()
                .toPath();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    @GetMapping("/find-by-user-and-job")
    public ResponseEntity<JobRegister> findByUserAndJob(@RequestParam(name = "user_id") long user_id, @RequestParam(name = "job_id") long job_id) {
        return ResponseEntity.ok().body(jobRegisterService.findByUserAndJob(user_id, job_id));
    }
}
