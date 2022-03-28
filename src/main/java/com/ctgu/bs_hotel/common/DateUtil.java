package com.ctgu.bs_hotel.common;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName StringToDate
 * Description
 * Create by luochuang
 * Date 2022/3/5 1:17 下午
 */
public class DateUtil {
    public static Date string2Date(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = simpleDateFormat.parse(date);
        return nowDate;
    }

    public static String getNowDateShort(String currentTime) {
        String currentTime_2 = currentTime.substring(0,10);
        return currentTime_2;
    }



    public static boolean isCross(Date leftStartDate, Date leftEndDate, Date rightStartDate, Date rightEndDate){
        boolean result =  ((leftStartDate.getTime() >= rightStartDate.getTime())
                && leftStartDate.getTime() < rightEndDate.getTime())
                ||
                ((leftStartDate.getTime() > rightStartDate.getTime())
                        && leftStartDate.getTime() <= rightEndDate.getTime())
                ||
                ((rightStartDate.getTime() >= leftStartDate.getTime())
                        && rightStartDate.getTime() < leftEndDate.getTime())
                ||
                ((rightStartDate.getTime() > leftStartDate.getTime())
                        && rightStartDate.getTime() <= leftEndDate.getTime());

        return result;//true 有交叉 false 无交叉
    }
}
