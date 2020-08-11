package org.sharefiles.root.controllers;


import org.sharefiles.root.responses.ErrorResponse;
import org.sharefiles.root.responses.FileUploadResponse;
import org.sharefiles.root.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
        boolean isFinished;
        if(file.isEmpty()) return new ResponseEntity<>(
                new ErrorResponse.Builder(HttpStatus.BAD_REQUEST, "File is empty").build(),
                HttpStatus.BAD_REQUEST);

        boolean isAuthenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();

        if(isAuthenticated) {
            isFinished = uploadService.uploadFileRegistered(file);
        } else {
            isFinished = uploadService.uploadFileAnon(file);
        }

        if(!isFinished)
            return new ResponseEntity<Object>(new FileUploadResponse(HttpStatus.NOT_FOUND, isAuthenticated,
                "Something went wrong with uploading your file!"), HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Object>(new FileUploadResponse(HttpStatus.OK, isAuthenticated,
                    "File has been uploaded"), HttpStatus.OK);



    }


}
