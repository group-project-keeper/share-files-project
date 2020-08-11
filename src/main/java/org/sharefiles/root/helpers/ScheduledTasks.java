package org.sharefiles.root.helpers;

import org.sharefiles.root.config.ShareFilesConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Service
public class ScheduledTasks {


    private static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private String todayFolderDirectory = OwnDateFormatter.getSimpleDateFormat();
    private String tomorrowFolderDirectory = OwnDateFormatter.getNextDayDate();
    private String dayAfterTomorrow = OwnDateFormatter.getDayAfterTomorrow();


    @Value("${upload.service.main.directory}")
    private String uploadDirectory;


    @Scheduled(cron = ShareFilesConfig.CRON_AT_NIGHT_TIME)
    public void createDirectoryDayAfterTomorrow(){
        createDirectoryFunction(ShareFilesConfig.ANONYMOUS_DIRECTORY + dayAfterTomorrow);
        createDirectoryFunction(ShareFilesConfig.REGISTERED_DIRECTORY + dayAfterTomorrow);

    }


    @PostConstruct
    public void createDirectoryForUploads(){
        createDirectoryFunction(ShareFilesConfig.ANONYMOUS_DIRECTORY + todayFolderDirectory);
        createDirectoryFunction(ShareFilesConfig.ANONYMOUS_DIRECTORY + tomorrowFolderDirectory);
        createDirectoryFunction(ShareFilesConfig.REGISTERED_DIRECTORY);
    }

    public void createDirectoryFunction(String directory){
        try{
        Files.createDirectories(Paths.get(directory));
        } catch (IOException e){
            logger.error("There has been an error " + e.getMessage() + e.getCause());
        }
    }

}
