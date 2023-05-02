package com.wudji;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Lesson {
    // public String week;
    private String name = "Innovation in China 中国智造，慧及全球 0123456789";
    private int index;

    private String timeStart;
    private String timeEnd;

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public long getTimeStart() throws ParseException {
        return stringToMillis(timeStart);
    }

    public long getTimeEnd() throws ParseException {
        return stringToMillis(timeEnd);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    private long stringToMillis(String timeString) throws ParseException {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat tf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat cf = new SimpleDateFormat("yyyy-MM-dd");
        c.setTime(tf.parse(cf.format(new Date(System.currentTimeMillis())) + " " + timeString));
        return c.getTimeInMillis();
    }
}
