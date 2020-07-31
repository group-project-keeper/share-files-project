package org.sharefiles.root.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class UploadService {


    @Value("${upload.service.main.directory}")
    private String shareFilesDirectory;

    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);



    @EventListener(ApplicationReadyEvent.class)
    public void createMainDirectory() {
        try {
            if(!new File(shareFilesDirectory).exists())
                Files.createDirectory(Paths.get(shareFilesDirectory));
        } catch (IOException e) {
            logger.error("There has been an error " + e.getMessage());
        }
    }

    public boolean uploadFile() {
        return false;
    }







}
