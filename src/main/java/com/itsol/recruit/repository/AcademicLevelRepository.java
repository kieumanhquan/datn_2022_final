package com.itsol.recruit.repository;

import com.itsol.recruit.entity.AcademicLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicLevelRepository extends JpaRepository<AcademicLevel, Long> {
    AcademicLevel findOneById(Long academicLevelId);

    List<AcademicLevel> findAll();
}
