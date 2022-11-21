package com.itsol.recruit.dto;

import com.itsol.recruit.core.CustomValidate.DueDateConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobDTO {

    Long id;

    @NotNull
    @Size(max = 150, message = "Over 150 characters")
    String name;

    @NotNull
    Long jobPositionId;

    @NotNull
    Integer numberExperience;

    @NotNull
    Long workingFormId;

    @NotNull
    @Size(max = 300, message = "Over 300 characters")
    String addressWork;

    @NotNull
    Long academicLevelId;

    @NotNull
    Long rankId;

    @NotNull
    Integer qtyPerson;

    @NotNull
    Date startRecruitmentDate;

    @NotNull
    @DueDateConstraint
    Date dueDate;

    String skills;

    @Size(max = 2000, message = "Over 2000 characters")
    String description;

    @Size(max = 2000, message = "Over 2000 characters")
    String benefits;

    @Size(max = 2000, message = "Over 2000 characters")
    String jobRequirement;

    @NotNull
    Integer salaryMax;

    @NotNull
    Integer salaryMin;

    @NotNull
    Long contactId;

    @NotNull
    Long creatorId;

    Date createDate;

    @NotNull
    Long updateUserId;

    @NotNull
    Date updateDate;

    @NotNull
    Long statusJobId;

    @NotNull
    Integer views;
}
