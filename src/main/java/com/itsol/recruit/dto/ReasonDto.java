package com.itsol.recruit.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReasonDto {
    private Long jobId;

    private Long statusId;

    private String reason;
}
