package org.sharefiles.root.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTasks {

    private static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Value("${upload.service.main.directory}")
    private String uploadDirectory;

    @Scheduled(cron = "", zone = "Europe/Warsaw")
    public void createDirectoryForCurrentDay(){
        String currentDayFolderName = OwnDateFormatter.getSimpleDateFormat();

    }
}
