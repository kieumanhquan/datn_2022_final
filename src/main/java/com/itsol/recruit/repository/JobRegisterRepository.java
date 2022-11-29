package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Job;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface JobRegisterRepository extends JpaRepository<JobRegister, Long> {

    JobRegister findOneById(Long id);

    @Query(value = "select u from job_register u where u.job.id = :id")
    List<JobRegister> findByJobId(@Param("id") Long id);

    @Query(value = "select count(u.id) from job_register u where u.statusJobRegister.id = :status and u.dateRegister between :start_date and :end_date")
    Long findByDateAndStatus(@Param("start_date") Date sDate, @Param("end_date") Date eDate,
                             @Param("status") Long statusId);

    @Query(value = "select count(u.id) from job_register u where u.dateRegister between :start_date and :end_date")
    Long findTotalRegister(@Param("start_date") Date sDate, @Param("end_date") Date eDate);

    @Query(value = "select count(u.id) from job_register u where u.statusJobRegister.id = :status and u.dateRegister between :start_date and :end_date group by SUBSTRING(u.dateRegister, 5, 7)")
    List<Long> findTotalByMonth(@Param("start_date") Date sDate, @Param("end_date") Date eDate,
                          @Param("status") Long statusId);

    JobRegister findByUserAndJob(User user, Job job);
}
