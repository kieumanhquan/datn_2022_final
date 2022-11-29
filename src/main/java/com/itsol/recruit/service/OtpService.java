package com.itsol.recruit.service;

import com.itsol.recruit.dto.MessageDto;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.OTP;
import com.itsol.recruit.entity.User;

import java.util.List;


public interface OtpService {
     List<OTP> getAll();

     OTP findById(Long id);

     OTP findByUserId(Long id);

     boolean edit(OTP otp);

     boolean delete(Long id);

     boolean check(OTP otp,User user);

     MessageDto sendOtp(UserDTO userDTO);

}
