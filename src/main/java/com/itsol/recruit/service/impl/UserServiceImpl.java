package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.MessageDto;
import com.itsol.recruit.dto.UserAndProfilesDto;
import com.itsol.recruit.dto.UserPaginationDto;
import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.entity.Role;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.ProfilesRepository;
import com.itsol.recruit.repository.RoleRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.UserService;
import com.itsol.recruit.web.vm.SearchUserVM;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;

    public final RoleRepository roleRepository;

    public final ProfilesRepository profilesRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ProfilesRepository profilesRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.profilesRepository = profilesRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new IllegalStateException("No user exists");
        }

    }

    @Override
    public User findUserByUserName(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new IllegalStateException("No user exists");
        }
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findOneByEmail(email).orElse(null);
    }

    @Override
    public List<User> findUserByRole(String role) {
        List<User> users = userRepository.findAll();
        List<User> userList = new ArrayList<>();
        for (User u : users) {
            for (Role role1 : u.getRoles()) {
                if (role1.getCode().equals(role)) {
                    userList.add(u);
                    break;
                }
            }
        }
        return userList;
    }

    @Override
    public User save(User user) {
        System.out.println("user input: " + user.toString());
        return userRepository.save(user);
    }

    @Override
    public MessageDto updateUser(User user) {
        MessageDto message = new MessageDto();
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            User userDb = userRepository.findByUserName(user.getUserName()).get();
            user.setPassword(userDb.getPassword());
            user.setUserName(userDb.getUserName());
            user.setId(userDb.getId());
            userRepository.save(user);
            message.setMessage("Successfully edited user information");
            message.setObj(true);
        } else {
            message.setMessage("Fail edited user information");
            message.setObj(false);
        }
        return message;
    }


    public UserPaginationDto find(SearchUserVM searchUserVM, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        UserPaginationDto userPaginationDto = new UserPaginationDto();
        userPaginationDto.setList(userRepository.findBySearchUserVm("%" + searchUserVM.getUserName().toLowerCase() + "%",
                "%" + searchUserVM.getPhoneNumber() + "%", "%" + searchUserVM.getEmail() + "%",
                pageable).stream().collect(Collectors.toList()));
        userPaginationDto.setTotalPage((long) userRepository.findBySearchUserVm("%" + searchUserVM.getUserName().toLowerCase() + "%",
                "%" + searchUserVM.getPhoneNumber() + "%", "%" + searchUserVM.getEmail() + "%",
                pageable).getTotalPages());
        return userPaginationDto;
    }

    @Override
    public UserAndProfilesDto findUserProfilesByUserName(String userName) {
        UserAndProfilesDto userAndProfilesDto = new UserAndProfilesDto();
        Optional<User> user = userRepository.findByUserName(userName);
        if (!user.isPresent()) {
            throw new IllegalStateException("No user exists");
        }

        userAndProfilesDto.setUser(user.get());
        if (profilesRepository.findOneByUser(user.get()) == null) {
            Profiles profile = new Profiles();
            profile.setUser(user.get());
            userAndProfilesDto.setProfiles(profile);
        } else {
            userAndProfilesDto.setProfiles(profilesRepository.findOneByUser(user.get()));
        }
        return userAndProfilesDto;
    }

    @Override
    public MessageDto updateUserProfiles(UserAndProfilesDto userAndProfilesDto) {
        MessageDto message = new MessageDto();
        try {
            userRepository.save(userAndProfilesDto.getUser());
            profilesRepository.save(userAndProfilesDto.getProfiles());
            message.setObj(true);
            message.setMessage("Update successful");
        } catch (Exception e) {
            message.setObj(false);
            message.setMessage("Update failed");
        }
        return message;
    }

    @Override
    public boolean activeFirstTime(String userName, String password) {
        User user;
        if (userRepository.findByUserName(userName).isPresent()) {
            user = userRepository.findByUserName(userName).get();
            user.setFirstTimeLogin(true);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean detective(Long userId) {
        User user;
        if (userRepository.findById(userId).isPresent()) {
            user = userRepository.findById(userId).get();
            user.setActive(!user.isActive());
            userRepository.save(user);
            return true;
        } else {
            return false;
        }

    }
}
