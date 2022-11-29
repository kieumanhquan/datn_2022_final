package com.itsol.recruit.repository.repoext;

import com.itsol.recruit.dto.JobPaginationDto;
import com.itsol.recruit.web.vm.SearchJobVM;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface JobRepositoryExt {
    JobPaginationDto search(SearchJobVM searchJobVM,String orderName, Integer pageNumber, Integer pageSize);

    JobPaginationDto getNewJob(Date numberDate, Integer pageNumber, Integer pageSize);

    JobPaginationDto getJobHighSalary(Integer salary, Integer pageNumber, Integer pageSize);

    JobPaginationDto getJobDue(Date numberDate, Integer pageNumber, Integer pageSize);
}
