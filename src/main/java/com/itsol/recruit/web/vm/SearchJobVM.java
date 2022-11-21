package com.itsol.recruit.web.vm;

import lombok.Data;

@Data
public class SearchJobVM {
    private String name;

    private Long statusId;

    private Integer salaryMin;

    private Integer salaryMax;

    private String addressWork;

    private String skills;
}
