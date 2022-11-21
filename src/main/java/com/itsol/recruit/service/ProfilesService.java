package com.itsol.recruit.service;

import com.itsol.recruit.entity.Profiles;

public interface ProfilesService {
    Profiles save(Profiles profiles);

    Profiles getProfileByUserId(Long id);


}
