package com.hziee.test;

import com.hziee.graph.MatrixGraph;
import com.hziee.graph.Scene;
import com.hziee.triple.Triple;

import java.util.Scanner;

public class Test {
    private static MatrixGraph<Scene> graph;
    private static Scene[] scenes;

    public static void main(String[] args) {
        init();
        hint();
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入你要执行的操作命令:");
        String command;
        while (!((command = sc.next()).equals("Q"))) {
            if (command.equals("A")) {
                System.out.print("请输入要查询的景点编号:");
                int i = sc.nextInt();
                Scene scene = scenes[i];
                System.out.println(scene.getName());
            } else if (command.equals("B")) {
                System.out.print("请输入要查询的景点编号(2个景点间以空格相隔):");
                int a = sc.nextInt();
                int b = sc.nextInt();
                graph.shortestPath(a, b);
            } else {
                System.out.println("没有该操作命令!");
            }
            System.out.print("请输入你要执行的操作命令:");
        }
        sc.close();
    }

    private static void init() {
        scenes = new Scene[]{
                new Scene(0, "综合体", ""),
                new Scene(1, "超市", ""),
                new Scene(2, "学生公寓", ""),
                new Scene(3, "图书馆", ""),
                new Scene(4, "体育馆", ""),
                new Scene(5, "食堂", ""),
                new Scene(6, "田径场", ""),
                new Scene(7, "格致楼", ""),
                new Scene(8, "笃行楼", ""),
                new Scene(9, "教学楼", ""),
                new Scene(10, "网球场", "")
        };
        Triple[] edges = new Triple[30];
        String[] a = {
                "0,2,7",
                "1,2,4", "1,4,1",
                "2,0,7", "2,1,4", "2,3,9", "2,5,2",
                "3,2,9", "3,5,11",
                "4,1,1", "4,6,2", "4,5,6",
                "5,2,2", "5,4,6", "5,6,7", "5,3,11", "5,9,14", "5,8,12",
                "6,4,2", "6,5,7", "6,7,5",
                "7,6,5", "7,8,3",
                "8,5,12", "8,7,3", "8,9,4",
                "9,5,14", "9,8,4", "9,10,8",
                "10,9,8"
        };
        for (int i = 0; i < a.length; i++) {
            String[] b = a[i].split(",");
            int row = Integer.parseInt(b[0]);
            int column = Integer.parseInt(b[1]);
            int value = Integer.parseInt(b[2]);
            edges[i] = new Triple(row, column, value);
        }
        graph = new MatrixGraph<>(scenes, edges);
    }

    private static void hint() {
        System.out.println("可选择的地点");
        for (Scene scene : scenes) {
            System.out.print(scene.getId() + ":" + scene.getName() + " ");
        }
        System.out.println("\n可选择的操作命令");
        System.out.println("A:任意景点的信息咨询 B:任意景点间的问路咨询 Q:退出\n");
    }
}
