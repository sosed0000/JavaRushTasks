package com.javarush.task.pro.task04.task0408;

import java.util.Scanner;

/* 
Максимум из введенных чисел
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Scanner scanner = new Scanner(System.in);
        int maxEven = Integer.MIN_VALUE;
        while (scanner.hasNextInt()) {
            int x = scanner.nextInt();
            if ((x % 2) == 0 && x > maxEven) maxEven = x;
        }
        System.out.println(maxEven);

    }
}