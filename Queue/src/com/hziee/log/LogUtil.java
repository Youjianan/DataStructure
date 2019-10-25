package com.hziee.log;

import com.hziee.model.Customer;
import com.hziee.model.Time;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*日志类，当某事件触发时写入日志文件*/
public class LogUtil {
    private static void writeLog(String log) {
        String filePath = "E:\\IDEA-work\\DataStructure\\Queue\\src\\log.txt";
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(filePath, true));
            out.write(log);
            out.newLine();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //理发店开始营业
    public static void startManage(Time time) {
        String log = "[" + time + "]   理发店开始营业";
        writeLog(log);
    }

    //理发店结束营业
    public static void finishManage(Time time) {
        String log = "[" + time + "]   理发店结束营业";
        writeLog(log);
    }

    //1名顾客开始理发
    public static void startHaircut(Time time, Customer customer) {
        String log = "[" + time + "]   ID为" + customer.getId() + " 的顾客开始理发";
        writeLog(log);
    }

    //1名顾客进入理发店
    public static void customerArrived(Time time, Customer customer) {
        String log = "[" + time + "]   ID为" + customer.getId() + " 的顾客进入了理发店";
        writeLog(log);
    }

    //1名顾客结束理发并离开理发店
    public static void finishHaircut(Time time, Customer customer) {
        String log = "[" + time + "]   ID为" + customer.getId() + " 的顾客结束理发并离开了理发店 " + customer;
        writeLog(log);
    }
}
