package com.itsol.recruit.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface  UploadService {

    void init();

    void save(String username, MultipartFile file, Long jobId);

    void saveAvatar(MultipartFile file, Long userId);

    Resource load(String filename);

    void deleteAll();

    Stream<Path> loadAll();
}
