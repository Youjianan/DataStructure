package com.hziee.model;

public class Customer {
    private int id;                //顾客ID
    private Time arrivedTime;      //顾客到达时间
    private Time departureTime;    //顾客离开时间
    private int totalHaircutTime;  //顾客理发所需时间

    public int haircutTime;        //顾客理发剩余时间

    public Customer(int id, Time arrivedTime, int haircutTime) {
        this.id = id;
        this.arrivedTime = arrivedTime;
        this.departureTime = null;
        this.totalHaircutTime = this.haircutTime = haircutTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getArrivedTime() {
        return arrivedTime;
    }

    public void setArrivedTime(Time arrivedTime) {
        this.arrivedTime = arrivedTime;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public int getStayTime() {
        return getDepartureTime().minutes() - getArrivedTime().minutes();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{到达时间=");
        sb.append(arrivedTime);
        sb.append(", 离开时间=");
        sb.append(departureTime);
        sb.append(", 理发所需时间=");
        sb.append(totalHaircutTime);
        return sb.append("min}").toString();
    }
}
