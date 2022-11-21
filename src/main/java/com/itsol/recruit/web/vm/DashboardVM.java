package com.itsol.recruit.web.vm;

import lombok.Data;

import java.util.Date;

@Data
public class DashboardVM {
    private Date startDate;

    private Date endDate;

    private Long statusId;
}
