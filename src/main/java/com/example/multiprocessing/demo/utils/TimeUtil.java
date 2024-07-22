package com.example.multiprocessing.demo.utils;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeUtil {
    //得到当前时间，准确到毫秒
    public static String getMilliTimeNow() {
        DateTimeFormatter df_year = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime date = LocalDateTime.now();
        String datestr = date.format(df_year);
        return datestr;
    }
}
