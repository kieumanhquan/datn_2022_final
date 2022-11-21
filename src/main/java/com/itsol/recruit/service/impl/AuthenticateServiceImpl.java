package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.MessageDto;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.OTP;
import com.itsol.recruit.entity.Role;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.OtpRepository;
import com.itsol.recruit.repository.ProfilesRepository;
import com.itsol.recruit.repository.RoleRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.AuthenticateService;
import com.itsol.recruit.service.email.EmailService;
import com.itsol.recruit.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService {

    private final UserMapper userMapper;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final EmailService emailService;

    public final ProfilesRepository profilesRepository;

    private final OtpRepository otpRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthenticateServiceImpl(UserMapper userMapper, RoleRepository
            roleRepository, UserRepository userRepository, EmailService emailService,
                                   ProfilesRepository profilesRepository, OtpRepository otpRepository,
                                   PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.profilesRepository = profilesRepository;
        this.otpRepository = otpRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Boolean signup(@NotNull UserDTO dto, String role) {
//        if (!userRepository.findOneByEmail(dto.getEmail()).isPresent()
//                && !userRepository.findByUserName(dto.getUserName()).isPresent()
//                && !userRepository.findByPhoneNumber(dto.getPhoneNumber()).isPresent())
        if (!userRepository.findByUserName(dto.getUserName()).isPresent()) {
            try {
                Set<Role> roles = roleRepository.findByCode(role);
                User user = userMapper.toEntity(dto);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setActive(true);
                user.setRoles(roles);
                user.setFirstTimeLogin(false);
                userRepository.save(user);
//                OTP otp = new OTP(user);
//                otpRepository.save(otp);
//                String link = emailService.buildActiveEmail(user.getName(), otp.getCode(), user.getId());
//                emailService.send(user.getEmail(), link, "Confirm email");
                return true;
            } catch (Exception e) {
                log.error("cannot save to database");
                return false;
            }
        } else return false;
    }

    @Override
    public MessageDto changePassword(UserDTO dto) {
        String message = "";
        try {
            System.out.println("email change password: " + dto.getPassword());
            Optional<User> user = userRepository.findOneByEmail(dto.getEmail());
            if (user.isPresent()) {
                OTP dbOtp = otpRepository.findByUser(user.get());
                User tempUser = user.get();
                System.out.println("old pass: " + tempUser.getPassword());
                if (dbOtp.isExpired()) {
                    message = "The otp code has expired";
                } else {
                    if (dbOtp.getCode().equals(dto.getOtp())) {
                        tempUser.setPassword(passwordEncoder.encode(dto.getPassword()));
                        tempUser.setFirstTimeLogin(false);
                        userRepository.save(tempUser);
                        message = "Change password successfully";
                    } else {
                        message = "The otp code is incorrect, please try again";
                    }
                }
            }
        } catch (Exception e) {
            message = "An error occurred while changing the password, please try again later";
            throw new IllegalStateException(message, e);
        }

        MessageDto messageDto = new MessageDto();
        messageDto.setObj(false);
        messageDto.setMessage(message);
        return messageDto;
    }

    @Override
    public String activeAccount(String otp, Long userId) {
        try {
            Optional<User> dbUser = userRepository.findById(userId);
            if (dbUser.isPresent()) {
                User user = dbUser.get();
                OTP dbOtp = otpRepository.findByUser(user);
                if (dbOtp.getCode().equals(otp)) {
                    user.setActive(true);
                    userRepository.save(user);
                    return "Account activation successful " +
                            "url:http://localhost:4200/auth";
                }
            } else {
                return "Account activation failed" +
                        "url:http://localhost:4200/auth";
            }
        } catch (Exception e) {
            throw new IllegalStateException("Account activation failed", e);
        }
        return "Account activation failed";
    }

}
