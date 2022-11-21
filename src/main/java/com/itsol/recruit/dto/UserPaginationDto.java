package com.itsol.recruit.dto;

import com.itsol.recruit.entity.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserPaginationDto {
    List<User> list;

    Long totalPage;
}
