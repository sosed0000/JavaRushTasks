package com.javarush.task.pro.task13.task1309;

import java.util.HashMap;

/* 
Успеваемость студентов
*/

public class Solution {
    public static HashMap<String, Double> grades = new HashMap<>();

    public static void main(String[] args) {
        addStudents();
        System.out.println(grades);
    }

    public static void addStudents() {
        //напишите тут ваш код
        grades.put("Holly", 5.0);
        grades.put("Jimmy", 4.2);
        grades.put("Harry", 4.2);
        grades.put("Puh", 2.5);
        grades.put("Ia", 3.2);
    }
}
