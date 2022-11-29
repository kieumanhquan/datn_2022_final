package com.itsol.recruit.repository.repoext;

import com.itsol.recruit.dto.JobRegisterPaginationDto;
import com.itsol.recruit.web.vm.SearchJobRegisterVM;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRegisterRepositoryExt {
    JobRegisterPaginationDto search(SearchJobRegisterVM searchJobRegisterVM, String orderName, Integer pageNumber, Integer pageSize);
}
