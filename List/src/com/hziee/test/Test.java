package com.hziee.test;

import com.hziee.model.City;
import com.hziee.singlylist.SinglyList;

public class Test {
    public static void main(String[] args) {
        City city1 = new City("杭州",1.0,2.5);
        City city2 = new City("上海",3.5,5.5);
        City city3 = new City("北京",20.5,13.3);
        City[] cities = new City[]{city1,city2,city3};
        SinglyList list = new SinglyList(cities);

        System.out.println("更新数据前：");
        System.out.println(list);
        System.out.println(list.size());
        System.out.println("更新数据后：");
        list.updateCity("北京",50,100);
        System.out.println(list);
        System.out.println(list.size());
    }
}
