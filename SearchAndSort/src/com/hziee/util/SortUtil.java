package com.hziee.util;

import com.hziee.bean.Student;

import java.util.List;

public class SortUtil {
    public static void quickSort(List<Student> list, int low, int high) {
        int start = low;
        int end = high;
        int key = list.get(low).getTotalScore();
        while (end > start) {
            //从后往前比较
            while (end > start && list.get(end).getTotalScore() <= key)
                end--;
            if (list.get(end).getTotalScore() >= key) {
                Student temp = list.get(end);
                list.set(end, list.get(start));
                list.set(start, temp);
            }
            //从前往后比较
            while (end > start && list.get(start).getTotalScore() >= key)
                start++;
            if (list.get(start).getTotalScore() <= key) {
                Student temp = list.get(start);
                list.set(start, list.get(end));
                list.set(end, temp);
            }
        }
        if (start > low) quickSort(list, low, start - 1);
        if (end < high) quickSort(list, end + 1, high);
    }

    public static void shellSort(List<Student> list) {
        for (int delta = list.size() / 2; delta > 0; delta /= 2) {
            for (int i = delta; i < list.size(); i++) {
                Student temp = list.get(i);
                int j;
                for (j = i - delta; j >= 0 && temp.getTotalScore() > list.get(j).getTotalScore(); j -= delta) {
                    list.set(j + delta, list.get(j));
                }
                list.set(j + delta, temp);
            }
        }
    }
}