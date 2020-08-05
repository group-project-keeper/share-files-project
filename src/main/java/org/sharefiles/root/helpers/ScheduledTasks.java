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


@Service
public class ScheduledTasks {


    private static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private String todayFolderDirectory = OwnDateFormatter.getSimpleDateFormat();

    @Value("${upload.service.main.directory}")
    private String uploadDirectory;

    @Scheduled(cron = "0 0 1 * * ?", zone = "Europe/Warsaw")
    //@Scheduled(fixedRate = 8000)
    public void createDirectoryForCurrentDay(){

        String tomorrowFolderDirectory = uploadDirectory + OwnDateFormatter.getNextDayDate();
        try {
            if(!new File(todayFolderDirectory).exists())
                Files.createDirectory(Paths.get(todayFolderDirectory));
            if(!new File(tomorrowFolderDirectory).exists())
                Files.createDirectory(Paths.get(tomorrowFolderDirectory));
        } catch (IOException e) {
            logger.error("There has been an error " + e.getMessage());
        }
    }

    @Scheduled(cron = "0 0 1 * * ?", zone="Europe/Warsaw")
    //@Scheduled(fixedRate= 8000)
    public void deleteOldFolders(){
        String folderToDelete = uploadDirectory + OwnDateFormatter.getFolderNameToDelete();
        try{
            FileUtils.deleteDirectory(new File(folderToDelete));
        } catch (IOException e){
            logger.error("There has been an error " + e.getMessage());
        }

    }
}
