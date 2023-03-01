package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;


    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt(1000);
        numberB = random.nextInt(1000);
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }

    private static String getAllNumbersBetween(int numberA, int numberB) {
        StringBuilder numbers = new StringBuilder();
        if (numberA <= numberB) {
            for (int i = numberA; i <= numberB; i++)
                numbers.append(i).append(" ");
        }
        if (numberA > numberB) {
            for (int i = numberA; i >= numberB; i--)
                numbers.append(i).append(" ");
        }
        return numbers.toString().trim();
    }
}