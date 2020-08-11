package org.sharefiles.root.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OwnDateFormatter {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    public static String getSimpleDateFormat(){
        return dateFormat.format(new Date());
    }

    public static String getNextDayDate(){
        Calendar tomorrowDate = Calendar.getInstance();
        tomorrowDate.add(Calendar.DAY_OF_MONTH, 1);
        return dateFormat.format(tomorrowDate.getTime());
    }

    public static String getDayAfterTomorrow(){
        Calendar tomorrowDate = Calendar.getInstance();
        tomorrowDate.add(Calendar.DAY_OF_MONTH, 2);
        return dateFormat.format(tomorrowDate.getTime());
    }

    public static  String getFolderNameToDelete(){
        Calendar tomorrowDate = Calendar.getInstance();
        tomorrowDate.add(Calendar.DAY_OF_MONTH, -7);

        return dateFormat.format(tomorrowDate);
    }


}
