package com.example.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class TimeExplainUtils {
    public static String timeExplain(LocalDateTime time) {
        long minutesDiff = ChronoUnit.MINUTES.between(time, LocalDateTime.now());
        if (minutesDiff < 1) return "刚刚";
        else if (minutesDiff < 60) return minutesDiff + "分钟前";
        else if (minutesDiff < 60 * 24) return minutesDiff / 60 + "小时前";
        else if (minutesDiff<60*24*30) return  minutesDiff/(60*24) +"天前";
        else if (minutesDiff<60*24*30*12) return  time.toString().split("T")[0].substring(5);
        else return time.toString().split("T")[0];
    }
}
