package com.itsol.recruit.dto;

import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.entity.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAndProfilesDto {

    User user;

    Profiles profiles;

}
