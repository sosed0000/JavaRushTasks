package com.javarush.task.pro.task05.task0506;

import java.util.Scanner;

/* 
Минимальное из N чисел
*/

public class Solution {
    public static int[] array;

    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        array = new int[N];
        for (int i = 0; i < array.length; i++)
            array[i] = scanner.nextInt();
        int min = Integer.MAX_VALUE;
        for (int x:
             array) {
            if (x < min) min = x;
        }
        System.out.println(min);
    }
}
