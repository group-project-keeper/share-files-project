package org.sharefiles.root.services;

import org.sharefiles.root.helpers.OwnDateFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadService {


    @Value("${upload.service.main.directory}")
    private String shareFilesDirectory;

    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);



    @EventListener(ApplicationReadyEvent.class)
    private void createMainDirectory() {
        try {
            if(!new File(shareFilesDirectory).exists())
                Files.createDirectory(Paths.get(shareFilesDirectory));
        } catch (IOException e) {
            logger.error("There has been an error " + e.getMessage());
        }
    }

    public boolean uploadFile(MultipartFile multipartFile) {
        Path uploadPath = Paths.get(shareFilesDirectory +
                OwnDateFormatter.getSimpleDateFormat() +
                "/" + multipartFile.getOriginalFilename());
        try {
            Files.copy(multipartFile.getInputStream(), uploadPath);

        } catch (IOException e) {
            logger.error("Error in uploading file fun() " + e.getMessage());
            return false;
        }

        return true;
    }







}
