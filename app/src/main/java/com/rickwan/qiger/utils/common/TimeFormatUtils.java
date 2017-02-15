package com.rickwan.qiger.utils.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author 万强
 * date 17/2/15 上午11:42
 * desc ${TODO}
 */
public class TimeFormatUtils {

    public static final String YMD_HMS= "yyyy-MM-dd HH:mm:ss";

    public static final String YMD_HM= "yyyy-MM-dd HH:mm";

    public static final String YMD= "yyyy-MM-dd";

    public static final String YM= "yyyy-MM";

    public static final String HMS= "HH:mm:ss";

    public static final String HM= "HH:mm";



    public  static String formatTime(String value,String type){

       return formatTime(value,type,type);
    }

    public static String formatTime(String value,String originalType,String formatType) {

        SimpleDateFormat sd = new SimpleDateFormat(originalType);
        SimpleDateFormat sd2 = new SimpleDateFormat(formatType);
        String newTime = null;
        try {
            Date date = sd.parse(value);
            newTime = sd2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newTime;
    }

}
