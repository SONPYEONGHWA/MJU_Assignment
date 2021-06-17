package com.example.workdiary.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년MM월dd일 hh:mm:ss");
        return dateFormat.format(date);
    }

    public String diffDate(String startTime, String endTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년MM월dd일 hh:mm:ss");
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

    public String subStringTime(String dateTime) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy년MM월dd일 hh:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

        String outputDate = "";
        try {
            Date date = inputFormat.parse(dateTime);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }
}
