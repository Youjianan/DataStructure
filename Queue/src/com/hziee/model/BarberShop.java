package com.hziee.model;

public class BarberShop {
    private static int chairs = 3;                                        //理发店的椅子数量
    private static Time openTime = new Time(8,0);           //理发店开门时间
    private static Time closeTime = new Time(20,0);         //理发店关门时间

    public static int getChairs() {
        return chairs;
    }

    public static Time getOpenTime() {
        return openTime;
    }

    public static Time getCloseTime() {
        return closeTime;
    }
}
