package com.example.workdiary.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년MM월dd일 hh:mm:ss");

    public String getCurrentTime() {
        return dateFormat.format(date);
    }

    public String diffDate(String startTime, String endTime) {
        String workTime = "";
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);
            long diff = end.getTime() - start.getTime();
            long seconds = diff / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;
            workTime = days + "일" + hours%24 + "시간" + minutes%60 + "분" + seconds%60 + "초";

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return workTime;
    }
}
