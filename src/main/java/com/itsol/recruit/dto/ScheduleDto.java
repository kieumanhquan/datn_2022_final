package com.itsol.recruit.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleDto {
    Long jobRegisterId;

    Long statusRegisterId;

    Date dateInterview;

    private String methodInterview;

    private String addressInterview;
}
