package com.hziee.test;

import com.hziee.bean.Student;
import com.hziee.util.RandomUtil;
import com.hziee.util.SortUtil;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Student> studentList = RandomUtil.initStudentList(10);
        System.out.println("排序前：");
        System.out.println("学号 姓名   语  数  英  物  总分");
        for (Student student : studentList) {
            student.calcTotal(1, 1, 3, 3);
            System.out.println(student);
        }
        SortUtil.quickSort(studentList, 0, studentList.size() - 1);
        //SortUtil.shellSort(studentList);
        System.out.println("================================");
        System.out.println("排序后：");
        System.out.println("学号 姓名   语  数  英  物  总分  名次");
        int i = 1;
        for (Student student : studentList) {
            if (i - 2 >= 0 && student.getTotalScore() == studentList.get(i - 2).getTotalScore()) {
                student.setRank(studentList.get(i - 2).getRank());
            } else {
                student.setRank(i);
            }
            i++;
            System.out.println(student);
        }
    }
}
