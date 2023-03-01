package com.javarush.task.task26.task2601;

/*
Почитать в инете про медиану выборки
*/

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Integer median = findMedian(array);
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o1 - median) - Math.abs(o2 - median);
            }
        };
        List<Integer> integerList = Arrays.asList(array);
        integerList.sort(comparator);
        integerList.toArray(array);
        return array;

    }

    private static Integer findMedian(Integer[] array) {
        Integer median;
        List<Integer> arrayList = Arrays.asList(array);
        Collections.sort(arrayList);
        int size = arrayList.size();
        if (size % 2 == 0) {
            median = ((arrayList.get(size / 2) + arrayList.get((size / 2) - 1)) / 2);
        } else {
            median = arrayList.get(size / 2);
        }

        return median;
    }
}
