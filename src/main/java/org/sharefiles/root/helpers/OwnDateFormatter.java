package org.sharefiles.root.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OwnDateFormatter {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

    public static String getSimpleDateFormat(){
        return dateFormat.format(new Date());
    }

    public static String getNextDayDate(){
        return null;
    }


}
