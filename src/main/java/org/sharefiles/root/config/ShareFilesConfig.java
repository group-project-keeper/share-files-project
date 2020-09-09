package org.sharefiles.root.config;


public class ShareFilesConfig {

    public static final String ANONYMOUS_DIRECTORY = "share-files/anonymous/";
    public static final String REGISTERED_DIRECTORY = "share-files/registered/";
    public static final String CRON_AT_NIGHT_TIME = "0 0 1 * * ?";
    public static final String CRON_AT_NIGHT_ZONE = "Europe/Warsaw";
    public static final int FILE_NAME_HASH_LENGTH = 10;
    public static final String MONGO_DATABASE_NAME = "files-data";
    public static final String MONGO_COLECTION_FILE_ANON = "anonymous files";
    public static final String MONGO_COLECTION_FILE_REIGSTERED = "registered files";
    public static final int FILE_NAME_MAX_LENGTH = 20;
    public static final int MAX_AMOUNT_UPLOADS_PER_USER = 30;
    public static final int MAX_AMOUNT_UPLOADS_PER_IP = 100;




}
