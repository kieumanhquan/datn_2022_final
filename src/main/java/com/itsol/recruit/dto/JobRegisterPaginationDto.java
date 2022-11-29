package com.itsol.recruit.dto;

import com.itsol.recruit.entity.JobRegister;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobRegisterPaginationDto {
    List<JobRegister> list;

    Long totalPage;
}
