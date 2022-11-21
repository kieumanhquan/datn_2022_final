package com.itsol.recruit.dto;

import com.itsol.recruit.core.CustomValidate.ContactNumberConstraint;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {

    @NotNull
    String name;

    @NotNull
    @Email
    String email;

    @NotNull
    @Size(max = 20, message = "The username must not exceed 20 characters")
    String userName;

    @NotNull
    @Size(min = 8, max = 16, message = "Password must be 8  to 16 characters")
    String password;

    @NotNull
    @ContactNumberConstraint
    String phoneNumber;

    String homeTown;

    String avatarName;

    String gender;

    String newPassword;

    Date birthDay;

    String otp;
}
