package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.repository.ProfilesRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.ProfilesService;
import org.springframework.stereotype.Service;

@Service
public class ProfilesServiceImpl implements ProfilesService {

    private final ProfilesRepository profilesRepository;

    private final UserRepository userRepository;

    public ProfilesServiceImpl(ProfilesRepository profilesRepository, UserRepository userRepository) {
        this.profilesRepository = profilesRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Profiles save(Profiles profiles) {
        return profilesRepository.save(profiles);
    }

    @Override
    public Profiles getProfileByUserId(Long id) {
        Profiles profiles = profilesRepository.findOneByUser(userRepository.findOneById(id));
        return profiles;
    }


}
