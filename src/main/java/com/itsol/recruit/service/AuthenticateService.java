package com.itsol.recruit.service;

import com.itsol.recruit.dto.MessageDto;
import com.itsol.recruit.dto.UserDTO;

public interface AuthenticateService {
    Boolean signup(UserDTO dto, String role);

    MessageDto changePassword(UserDTO dto);

    String activeAccount(String otp, Long userId);
}
