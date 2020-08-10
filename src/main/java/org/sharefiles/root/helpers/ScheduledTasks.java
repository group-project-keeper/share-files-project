package org.sharefiles.root.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import org.sharefiles.root.config.ShareFilesConfig;

import javax.annotation.PostConstruct;


@Service
public class ScheduledTasks {


    private static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private String todayFolderDirectory = OwnDateFormatter.getSimpleDateFormat();
    private String tomorrowFolderDirectory = OwnDateFormatter.getNextDayDate();
    private String dayAfterTomorrow = OwnDateFormatter.getDayAfterTomorrow();


    @Value("${upload.service.main.directory}")
    private String uploadDirectory;


    @Scheduled(cron = ShareFilesConfig.cronRunAtNight)
    public void createDirectoryDayAfterTomorrow(){
        createDirectoryFunction(ShareFilesConfig.anonymousDirectory+dayAfterTomorrow);
        createDirectoryFunction(ShareFilesConfig.registeredUserDirectory+dayAfterTomorrow);

    }


    @PostConstruct
    public void createDirectoryForUploads(){
        createDirectoryFunction(ShareFilesConfig.anonymousDirectory+todayFolderDirectory);
        createDirectoryFunction(ShareFilesConfig.anonymousDirectory+tomorrowFolderDirectory);
        createDirectoryFunction(ShareFilesConfig.registeredUserDirectory);
    }

    public void createDirectoryFunction(String directory){
        try{
        Files.createDirectories(Paths.get(directory));
        } catch (IOException e){
            logger.error("There has been an error " + e.getMessage() + e.getCause());
        }
    }

}
