package com.hziee.model;

public class Time implements Comparable<Time> {
    private int hour;
    private int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public Time(int minute) {
        int totalMinute = this.hour * 60 + this.minute + minute;
        this.hour = totalMinute / 60;
        this.minute = totalMinute % 60;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public Time add(int minute) {
        return new Time(this.minutes() + minute);
    }

    /*时间经过1分钟*/
    public void goBy() {
        if (this.minute != 59) {
            this.minute++;
        } else {
            this.hour++;
            this.minute = 0;
        }
    }

    /*将时间转换为总分钟*/
    public int minutes() {
        return this.hour * 60 + this.minute;
    }

    private String format(int num) {
        return (num >= 10) ? "" + num : "0" + num;
    }

    @Override
    public int compareTo(Time time) {
        return Integer.compare(this.minutes(), time.minutes());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(format(hour));
        sb.append(":");
        sb.append(format(minute));
        return sb.toString();
    }
}
