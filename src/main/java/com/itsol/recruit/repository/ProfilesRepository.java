package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilesRepository extends JpaRepository<Profiles,Long> {

    Profiles findOneByUser(User oneById);
}
