package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        long a = System.currentTimeMillis();


        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        for (int c = 0; c < 1000; c++) {
            int count1 = getRectangleCount(a1);
            System.out.println("count = " + count1 + ". Должно быть 2");
            int count2 = getRectangleCount(a2);
            System.out.println("count = " + count2 + ". Должно быть 4");
        }


        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) );

    }

    public static int getRectangleCount(byte[][] a) {
        a = a.clone();
        for (int y = 0; y < a.length; y++) {
            for (int x = 0; x < a[0].length - 1; x++) {
                if (a[y][x + 1] == 1) {
                    a[y][x] = 0;
                }
            }
        }
        for (int x = 0; x < a[0].length; x++) {
            for (int y = 0; y < a.length - 1; y++) {
                if (a[y + 1][x] == 1) {
                    a[y][x] = 0;
                }
            }
        }

        int count = 0;
        for (byte[] bytes : a) {
            for (int i = 0; i < a.length; i++) {
                count += bytes[i];
            }
        }
        return count;
    }
}
