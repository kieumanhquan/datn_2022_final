package com.itsol.recruit.web.rest;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.FileInfo;
import com.itsol.recruit.service.UploadService;
import com.itsol.recruit.utils.ResponseMessage;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class UploadController {

    private final UploadService uploadCVService;

    public UploadController(UploadService uploadCVService) {
        this.uploadCVService = uploadCVService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file
            , @RequestParam("username") String username , @RequestParam("jobId") long jobId ) {
        String message;
        try {
            uploadCVService.save(username, file, jobId);
            message = "Uploaded cv and apply job successfully: " + file.getOriginalFilename();
            ResponseMessage responseMessage = new ResponseMessage(message);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            message = "Could not upload and register job the file: " + file.getOriginalFilename() + "!";
            ResponseMessage responseMessage = new ResponseMessage(message);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseMessage);
        }
    }

    @PostMapping("/uploadAvatar")// up avata
    public ResponseEntity<ResponseMessage> uploadAvatar(@RequestParam("file") MultipartFile file
            , @RequestParam("userId") Long userId ) {
        String message;
        try {
            uploadCVService.saveAvatar( file, userId);
            message = "Uploaded avatarName successfully: " + file.getOriginalFilename();
            ResponseMessage responseMessage = new ResponseMessage(message);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            message = "Could not upload and register job the file: " + file.getOriginalFilename() + "!";
            ResponseMessage responseMessage = new ResponseMessage(message);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseMessage);
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = uploadCVService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(UploadController.class, "getFile", path.getFileName().toString()).build().toString();
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }


    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = uploadCVService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
