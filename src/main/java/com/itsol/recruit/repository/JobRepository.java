package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Job findOneById(Long id);

    @Query(value = "select count(u.views) from job u where u.createDate between :start_date and :end_date")
    Long findTotalViews(@Param("start_date") Date sDate, @Param("end_date") Date eDate);

    @Query(value = "select count(u) from job u where u.createDate between :start_date and :end_date")
    Long findTotalJob(@Param("start_date") Date sDate, @Param("end_date") Date eDate);

    @Query(value = "select count(u) from job u where u.createDate between :start_date and :end_date and u.dueDate between :SEVEN_DAY_BEFORE_CURRENT_DATE and CURRENT_DATE")
    Long findTotalJobDueDate(@Param("start_date") Date sDate, @Param("end_date") Date eDate, @Param("SEVEN_DAY_BEFORE_CURRENT_DATE") Date Date);

}
