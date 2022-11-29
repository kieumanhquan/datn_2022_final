package com.itsol.recruit.repository;

import com.itsol.recruit.entity.OTP;


import com.itsol.recruit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository  extends JpaRepository<OTP, Long> {

    OTP  findByUser(User user);

    OTP findOneByUser(User user);
}
