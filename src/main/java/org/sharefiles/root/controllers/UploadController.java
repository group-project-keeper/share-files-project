package org.sharefiles.root.controllers;


import org.sharefiles.root.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class UploadController {

    @PostMapping("/upload")
    public Object uploadFile(@RequestBody MultipartFile file) {
        if(file.isEmpty()) return new ErrorResponse.Builder(HttpStatus.BAD_REQUEST, "File is empty").build();


//        return ResponseEntity.ok(HttpStatus.OK, "");
        return null;
    }


}
