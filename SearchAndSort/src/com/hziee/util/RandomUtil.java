package com.hziee.util;

import com.hziee.bean.Student;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomUtil {
    /**
     * 初始化学生列表
     *
     * @param num 学生数量
     * @return 学生列表
     */
    public static List<Student> initStudentList(int num) {
        List<Student> studentList = new LinkedList<>();
        for (int i = 1; i <= num; i++) {
            Student student = new Student();
            student.setId(i);
            student.setName("学生" + i);
            student.setChinese(randomScore());
            student.setMath(randomScore());
            student.setEnglish(randomScore());
            student.setPhysics(randomScore());
            studentList.add(student);
        }
        return studentList;
    }

    private static int randomScore() {
        Random random = new Random();
        return random.nextInt(51) + 50;
    }
}
