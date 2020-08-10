package org.sharefiles.root.config;

import org.springframework.scheduling.annotation.Scheduled;

public class ShareFilesConfig {

    public static final String anonymousDirectory = "share-files/anonymous/";
    public static final String registeredUserDirectory = "share-files/registered/";
    public static final String cronRunAtNight = "cron = \"0 0 1 * * ?\", zone = \"Europe/Warsaw\"";

}

//@Scheduled(cron = "0 0 1 * * ?", zone = "Europe/Warsaw")
