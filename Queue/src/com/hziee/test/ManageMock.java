package com.hziee.test;

import com.hziee.list.SeqList;
import com.hziee.log.LogUtil;
import com.hziee.model.BarberShop;
import com.hziee.model.Customer;
import com.hziee.model.Time;
import com.hziee.queue.LinkedQueue;

import java.util.Random;

/*经营模拟类*/
public class ManageMock {
    private static int chairs = BarberShop.getChairs();                          //理发店椅子数
    private static Time openTime = BarberShop.getOpenTime();                     //正常开门时间
    private static Time closeTime = BarberShop.getCloseTime();                   //正常关门时间
    private static Time currentTime = new Time(openTime.minutes());              //当前时间
    private static LinkedQueue<Customer> customerQueue = new LinkedQueue<>();    //将要理发的顾客队列
    private static LinkedQueue<Customer> waitQueue = new LinkedQueue<>();        //等待理发的顾客队列
    private static SeqList<Customer> haircutList = new SeqList<>(chairs);        //正在理发的顾客顺序表
    private static float sumStayTime = 0.0f;                                     //所有顾客的逗留时间总数（分钟）
    private static float sumWaitCustomer = 0.0f;                                 //排队等候理发的总人数（人/分钟）
    private static float sumFreeChairs = 0.0f;                                   //空余椅子的总数（人/分钟）

public static void main(String[] args) {
    initCustomers();                           //初始化顾客列表
    int customerNum = customerQueue.size();    //到达的顾客人数
    //////////开始一天模拟经营//////////
    LogUtil.startManage(currentTime);          //日志记录开门事件
    while (currentTime.compareTo(closeTime) < 0 || !haircutList.isEmpty()) {
        currentTime.goBy();                    //模拟时间过去1分钟
        updateHaircutList();                   //更新正在理发的顾客顺序表
        updateWaitQueue();                     //更新等待理发的顾客队列
        updateCustomerQueue();                 //更新将要理发的顾客队列
    }
    LogUtil.finishManage(currentTime);         //日志记录关门事件
    int manageTime = currentTime.minutes() - openTime.minutes();  //营业时间
    //////////结束一天模拟经营//////////
    System.out.println("到达的顾客人数: " + customerNum);
    System.out.println("顾客在店内的平均逗留时间: " + String.format("%.3f", sumStayTime / customerNum));
    System.out.println("排队等候理发的平均人数: " + String.format("%.3f", sumWaitCustomer / manageTime));
    System.out.println("在营业时间内空椅子的平均数: " + String.format("%.3f", sumFreeChairs / manageTime));
}

    /*更新正在理发的顾客顺序表*/
    private static void updateHaircutList() {
        for (int i = 0; i < haircutList.size(); i++) {
            Customer customer = haircutList.get(i);
            if (customer.haircutTime != 1) {
                customer.haircutTime -= 1;
            } else {
                customer.setDepartureTime(new Time(currentTime.minutes()));
                sumStayTime += customer.getStayTime();
                LogUtil.finishHaircut(currentTime, customer);
                haircutList.remove(i);
                i--;
            }
        }
    }

    /*更新等待理发的顾客队列*/
    private static void updateWaitQueue() {
        while (!waitQueue.isEmpty() && haircutList.size() != chairs) {
            Customer customer = waitQueue.peek();
            LogUtil.startHaircut(currentTime, customer);
            haircutList.insert(customer);
            waitQueue.poll();
        }
    }

    /*更新将要理发的顾客队列*/
    private static void updateCustomerQueue() {
        Customer headOfQueue = customerQueue.peek();
        if (headOfQueue != null) {
            if (headOfQueue.getArrivedTime().compareTo(currentTime) == 0) {
                if (waitQueue.isEmpty() && haircutList.size() != chairs) {
                    LogUtil.customerArrived(currentTime, headOfQueue);
                    LogUtil.startHaircut(currentTime, headOfQueue);
                    haircutList.insert(headOfQueue);
                } else {
                    LogUtil.customerArrived(currentTime, headOfQueue);
                    waitQueue.add(headOfQueue);
                }
                customerQueue.poll();
            }
        }
        sumWaitCustomer += waitQueue.size();                   //记录排队等候理发人数
        sumFreeChairs += (chairs - haircutList.size());        //记录空余椅子总数
    }

    /*初始化一天的顾客*/
    private static void initCustomers() {
        Random random = new Random();
        Time time = new Time(currentTime.getHour(), currentTime.getMinute());
        time = time.add(random.nextInt(10) + 5);
        int id = 0;
        while (time.compareTo(closeTime) < 0) {
            int haircutTime = random.nextInt(25) + 15;
            Customer customer = new Customer(++id, time, haircutTime);
            customerQueue.add(customer);
            time = time.add(random.nextInt(10) + 5);
        }
    }
}
