package com.itsol.recruit.service;

import com.itsol.recruit.dto.JobRegisterPaginationDto;
import com.itsol.recruit.dto.ReasonDto;
import com.itsol.recruit.dto.ScheduleDto;
import com.itsol.recruit.dto.StatusRegisterDto;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.web.vm.SearchJobRegisterVM;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface JobRegisterService {
    JobRegister save(JobRegister jobRegister);

    JobRegisterPaginationDto find(SearchJobRegisterVM searchJobRegisterVM, int pageNumber, int pageSize);

    JobRegisterPaginationDto sortByName(SearchJobRegisterVM searchJobRegisterVM, int pageNumber, int pageSize);

    JobRegister getById(Long id);

    List<JobRegister> getByJobId(Long id);

    Long getByDateAndStatus(Date sDate, Date eDate, Long id);

    List<Long> getTotalByMonth(Date sDate, Date eDate, Long id);

    Long getTotalRegister(Date sDate, Date eDate);

    JobRegister updateStatus(StatusRegisterDto statusRegisterDto);

    JobRegister schedule(ScheduleDto scheduleDto);

    Resource downloadCv(Long applicantId) throws IOException;

    JobRegister updateReason(ReasonDto reasonDto);

    JobRegister findByUserAndJob(Long userId, Long jobId);
}
