package com.javarush.task.task36.task3602;

/*
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/

import java.util.Collections;

public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        for (Class clazz : Collections.class.getDeclaredClasses()) {
            if (clazz.getSimpleName().equals("EmptyList")) {
                return clazz;
            }
        }
        return null;
    }
}
