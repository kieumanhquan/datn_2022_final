package com.itsol.recruit.service;

import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.dto.JobPaginationDto;
import com.itsol.recruit.dto.ReasonDto;
import com.itsol.recruit.dto.StatusDto;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.web.vm.SearchJobVM;

import java.util.Date;
import java.util.List;

public interface JobService {

    JobPaginationDto find(SearchJobVM searchJobVM, int pageNumber, int pageSize);

    JobPaginationDto sortByName(SearchJobVM searchJobVM, int pageNumber, int pageSize);

    Job add(JobDTO jobDTO);

    Job getById(Long id);

    List<Job> findAll();

    JobPaginationDto getNewJob(Integer numberDay, int pageNumber, int pageSize);

    JobPaginationDto getJobHighSalary(Integer salary, int pageNumber, int pageSize);

    JobPaginationDto getJobDue(Integer numberDay, int pageNumber, int pageSize);

    Long getTotalJobDueDate(Date startDate, Date endDate);

    Long getTotalJob(Date startDate, Date endDate);

    Long getTotalViews(Date startDate, Date endDate);

    Job updateStatus(StatusDto statusDto);

    Job updateReason(ReasonDto reasonDto);

    void delete(long id);

    void addView(long id);
}
