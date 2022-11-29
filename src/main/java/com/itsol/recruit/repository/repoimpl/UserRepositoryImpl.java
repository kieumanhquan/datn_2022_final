package com.itsol.recruit.repository.repoimpl;

import com.itsol.recruit.dto.UserPaginationDto;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.repoext.UserRepositoryExt;
import com.itsol.recruit.web.vm.SearchJobVM;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepositoryExt {
    @Override
    public User getAllUser() {
        return null;
    }

    @Override
    public UserPaginationDto search(SearchJobVM searchJobVM, Integer pageNumber, Integer pageSize) {
        return null;
    }
}
