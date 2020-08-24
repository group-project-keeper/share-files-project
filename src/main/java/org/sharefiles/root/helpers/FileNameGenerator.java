package org.sharefiles.root.helpers;

import org.sharefiles.root.config.ShareFilesConfig;

import java.security.SecureRandom;
import java.util.Locale;

public class FileNameGenerator {

    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String lower = upper.toLowerCase(Locale.ROOT);
    public static final String digits = "0123456789";
    public static final String alphanum = upper + lower + digits;

    private static String todayFolderDirectory = OwnDateFormatter.getSimpleDateFormat();

    static SecureRandom random = new SecureRandom();

    static int counter=+1;

    //TODO implement in both function - Counter in FileNames
//  20200817# nyiQLWrf2J1 #test-file.txt
    public static String generateAnonFileNameHash(String originalFileName) {
        StringBuilder sb = new StringBuilder();
        sb.append(todayFolderDirectory);
        for (int i = 0; i <= ShareFilesConfig.FILE_NAME_HASH_LENGTH; i++) {
            sb.append(alphanum.charAt(random.nextInt(alphanum.length())));
        }
        sb.append(counter);
        sb.append(originalFileName);
        return sb.toString();
    }
    //10znakków  #9
//   2020 08 23 # uS2tMCDJ1 # test-file.txt
    public static String generateRegisteredFileNameHash(String originalFileName){
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        sb.append(todayFolderDirectory);
        for (int i=0; i<= ShareFilesConfig.FILE_NAME_HASH_LENGTH; i++){
            sb.append(alphanum.charAt(random.nextInt(alphanum.length())));
        }
        sb.append(counter);
        sb.append(originalFileName);
        return sb.toString();
    }

}
