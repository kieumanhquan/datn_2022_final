package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.JobRegisterPaginationDto;
import com.itsol.recruit.dto.ReasonDto;
import com.itsol.recruit.dto.ScheduleDto;
import com.itsol.recruit.dto.StatusRegisterDto;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.JobRegisterRepository;
import com.itsol.recruit.repository.JobRepository;
import com.itsol.recruit.repository.StatusJobRegisterRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.repository.repoext.JobRegisterRepositoryExt;
import com.itsol.recruit.service.JobRegisterService;
import com.itsol.recruit.service.email.EmailService;
import com.itsol.recruit.utils.CommonConst;
import com.itsol.recruit.web.vm.SearchJobRegisterVM;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class JobRegisterServiceImpl implements JobRegisterService {
    private final Path root = Paths.get(CommonConst.DIRECTORY_UPLOAD_CV);

    private final JobRegisterRepository jobRegisterRepository;

    private final StatusJobRegisterRepository statusJobRegisterRepository;

    private final EmailService emailService;

    private final JobRegisterRepositoryExt jobRegisterRepositoryExt;

    private final UserRepository userRepository;

    private final JobRepository jobRepository;

    public JobRegisterServiceImpl(JobRegisterRepository jobRegisterRepository, StatusJobRegisterRepository statusJobRegisterRepository, EmailService emailService, JobRegisterRepositoryExt jobRegisterRepositoryExt, UserRepository userRepository, JobRepository jobRepository) {
        this.jobRegisterRepository = jobRegisterRepository;
        this.statusJobRegisterRepository = statusJobRegisterRepository;
        this.emailService = emailService;
        this.jobRegisterRepositoryExt = jobRegisterRepositoryExt;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public JobRegister save(JobRegister jobRegister){
        return jobRegisterRepository.save(jobRegister);
    }

    @Override
    public JobRegisterPaginationDto find(SearchJobRegisterVM searchJobRegisterVM, int pageNumber, int pageSize) {
        return jobRegisterRepositoryExt.search(searchJobRegisterVM,"job_register.date_register",pageNumber,pageSize);
    }

    @Override
    public JobRegisterPaginationDto sortByName(SearchJobRegisterVM searchJobRegisterVM, int pageNumber, int pageSize) {
        return jobRegisterRepositoryExt.search(searchJobRegisterVM,"users.name",pageNumber,pageSize);
    }

    @Override
    public JobRegister getById(Long id) {
        return jobRegisterRepository.findOneById(id);
    }

    @Override
    public List<JobRegister> getByJobId(Long id) {
        return jobRegisterRepository.findByJobId(id);
    }

    @Override
    public Long getByDateAndStatus(Date sDate, Date eDate, Long id) {
        return jobRegisterRepository.findByDateAndStatus(sDate, eDate, id);
    }

    @Override
    public List<Long> getTotalByMonth(Date sDate, Date eDate, Long id) {
        return jobRegisterRepository.findTotalByMonth(sDate, eDate, id);
    }

    @Override
    public Long getTotalRegister(Date sDate, Date eDate) {
        return jobRegisterRepository.findTotalRegister(sDate, eDate);
    }


    @Override
    public JobRegister updateStatus(StatusRegisterDto statusRegisterDto){
       JobRegister jobRegister = jobRegisterRepository.findOneById(statusRegisterDto.getJobRegisterId());
       jobRegister.setStatusJobRegister(statusJobRegisterRepository.findOneById(statusRegisterDto.getStatusRegisterId()));
        return jobRegisterRepository.save(jobRegister);
    }

    @Override
    public JobRegister schedule(ScheduleDto scheduleDto){
        JobRegister jobRegister = jobRegisterRepository.findOneById(scheduleDto.getJobRegisterId());
        jobRegister.setStatusJobRegister(statusJobRegisterRepository.findOneById(scheduleDto.getStatusRegisterId()));
        jobRegister.setDateInterview(scheduleDto.getDateInterview());
        jobRegister.setMethodInterview(scheduleDto.getMethodInterview());
        jobRegister.setAddressInterview(scheduleDto.getAddressInterview());
        User user = jobRegister.getUser();
        String link=emailService.buildSchedule(jobRegister);
            emailService.send(user.getEmail(),link, "Schedule an interview");
        return jobRegisterRepository.save(jobRegister);
    }

    @Override
    public Resource downloadCv(Long applicantId) throws IOException {
        JobRegister jobRegister = jobRegisterRepository.findOneById(applicantId);
        if (ObjectUtils.isEmpty(jobRegister)) {
            throw new NullPointerException("Could not found applicant");
        }
        String cvFilePath = jobRegister.getCv();
        Path file = root.resolve(cvFilePath);
        Resource resource = new UrlResource(file.toUri());

        if (!resource.exists() && !resource.isReadable()) {
            throw new RuntimeException("Could not read the file!");
        }
        return resource;
    }

    @Override
    public JobRegister updateReason(ReasonDto reasonDto){
        JobRegister jobRegister = jobRegisterRepository.findOneById(reasonDto.getJobId());
        jobRegister.setStatusJobRegister(statusJobRegisterRepository.findOneById(reasonDto.getStatusId()));
        jobRegister.setReason(reasonDto.getReason());
        return jobRegisterRepository.save(jobRegister);
    }


    @Override
    public JobRegister findByUserAndJob(Long userId, Long jobId){
        User user = userRepository.findOneById(userId);
        Job job = jobRepository.findOneById(jobId);
        return jobRegisterRepository.findByUserAndJob(user,job);
    }


}
