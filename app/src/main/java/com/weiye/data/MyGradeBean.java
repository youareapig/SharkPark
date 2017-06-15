package com.weiye.data;

/**
 * Created by DELL on 2017/6/15.
 */
public class MyGradeBean {
    private String Time;
    private String Week;
    private String Date;
    private String Content;

    public MyGradeBean(String time, String week, String date, String content) {
        Time = time;
        Week = week;
        Date = date;
        Content = content;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getWeek() {
        return Week;
    }

    public void setWeek(String week) {
        Week = week;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
