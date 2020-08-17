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

    public static String generateFileNameHash(String originalFileName){
        StringBuilder sb = new StringBuilder();
        sb.append(todayFolderDirectory);
        for (int i = 0; i< ShareFilesConfig.FILE_NAME_HASH_LENGTH; i++){
            sb.append(alphanum.charAt(random.nextInt(alphanum.length())));
        }
        sb.append(counter);
        sb.append(originalFileName);
        return sb.toString();
    }


}
