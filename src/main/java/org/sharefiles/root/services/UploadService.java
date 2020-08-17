package org.sharefiles.root.services;

import org.sharefiles.root.config.ShareFilesConfig;
import org.sharefiles.root.model.User;
import org.sharefiles.root.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class UploadService {


    @Value("${upload.service.main.directory}")
    private String shareFilesDirectory;

    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);

    @Autowired
    private UserRepository userRepository;


    @EventListener(ApplicationReadyEvent.class)
    private void createMainDirectory() {
        try {
            if(!new File(shareFilesDirectory).exists())
                Files.createDirectory(Paths.get(shareFilesDirectory));
        } catch (IOException e) {
            logger.error("There has been an error " + e.getMessage());
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    private void createChildDirectories() {
        try {
            Files.createDirectory(Paths.get(ShareFilesConfig.ANONYMOUS_DIRECTORY));
            Files.createDirectory(Paths.get(ShareFilesConfig.REGISTERED_DIRECTORY));
        } catch (Exception e) {
            logger.error("Error creating child dirs or they already exist", e.getCause());
        }
    }

    public boolean uploadFileRegistered(MultipartFile multipartFile) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> uploadUser = userRepository.findByUsername(username);

        if(uploadUser.isPresent()) {
            Path uploadPath = Paths.get(ShareFilesConfig.REGISTERED_DIRECTORY + uploadUser.get().getUploadDirName()
                    + multipartFile.getOriginalFilename());
            try {
                Files.copy(multipartFile.getInputStream(), uploadPath);

            } catch (IOException e) {
                logger.error("Error in uploading REGIS file function() " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean uploadFileAnon(MultipartFile multipartFile) {
        Path uploadPath = Paths.get(ShareFilesConfig.ANONYMOUS_DIRECTORY
                + multipartFile.getOriginalFilename());
        try {
            Files.copy(multipartFile.getInputStream(), uploadPath);
            return true;
        } catch (IOException e) {
            logger.error("Error in uploading ANON file function() " + e.getMessage());
            return false;
        }

    }







}
