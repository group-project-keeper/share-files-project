package org.sharefiles.root.controllers;


import org.sharefiles.root.responses.ErrorResponse;
import org.sharefiles.root.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class UploadController {


    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public Object uploadFile(@RequestParam("file") MultipartFile file) {
        if(file.isEmpty()) return new ErrorResponse.Builder(HttpStatus.BAD_REQUEST, "File is empty").build();

        if(uploadService.uploadFile(file))
            return ResponseEntity.ok();

        return new ResponseEntity<>(
                new ErrorResponse.Builder(HttpStatus.CONFLICT, "Something went wrong while upload, please try again").build(),
                HttpStatus.CONFLICT);

    }


}
