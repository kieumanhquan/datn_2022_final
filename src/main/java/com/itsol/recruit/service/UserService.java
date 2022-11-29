package com.itsol.recruit.service;

import com.itsol.recruit.dto.MessageDto;
import com.itsol.recruit.dto.UserAndProfilesDto;
import com.itsol.recruit.dto.UserPaginationDto;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.web.vm.SearchUserVM;

import java.util.List;

public interface UserService {

    public List<User> getAllUser();

    public User findById(Long id);

    public User findUserByUserName(String userName);

    User findByEmail(String email);

    List<User> findUserByRole(String role);

    User save(User user);

    MessageDto updateUser(User user);

    UserPaginationDto find(SearchUserVM searchUserVM, int pageNumber, int pageSize);

    UserAndProfilesDto findUserProfilesByUserName(String username);

    MessageDto updateUserProfiles(UserAndProfilesDto userAndProfilesDto);

    boolean activeFirstTime(String userName, String password);

    boolean detective(Long userId);
}
