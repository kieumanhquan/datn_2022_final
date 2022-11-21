package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.AcademicLevel;
import com.itsol.recruit.repository.AcademicLevelRepository;
import com.itsol.recruit.service.AcademicLevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AcademicLevelServiceImpl implements AcademicLevelService {
    final
    AcademicLevelRepository academicLevelRepository;

    public AcademicLevelServiceImpl(AcademicLevelRepository academicLevelRepository) {
        this.academicLevelRepository = academicLevelRepository;
    }

    @Override
    public List<AcademicLevel> getAllAcademicLevel() {
        return academicLevelRepository.findAll();
    }
}
