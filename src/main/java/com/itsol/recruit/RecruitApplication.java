package com.itsol.recruit;

import com.itsol.recruit.service.UploadService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;


@SpringBootApplication
@EnableScheduling
public class RecruitApplication implements CommandLineRunner {

    @Resource
    UploadService uploadService;

    public static void main(String[] args) {
        SpringApplication.run(RecruitApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
        System.out.println("create root directory root to upload cv");
        uploadService.init();
    }

}
