package com.itsol.recruit.repository.repoext;

import com.itsol.recruit.dto.JobPaginationDto;
import com.itsol.recruit.dto.UserPaginationDto;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.web.vm.SearchJobVM;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryExt {
    public User getAllUser();

    UserPaginationDto  search(SearchJobVM searchJobVM, Integer pageNumber, Integer pageSize);
}
