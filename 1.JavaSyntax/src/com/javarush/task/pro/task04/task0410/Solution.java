package com.javarush.task.pro.task04.task0410;

import java.util.Scanner;

/* 
Второе минимальное число из введенных
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Scanner console = new Scanner(System.in);
        int min = Integer.MAX_VALUE;
        int max = Integer.MAX_VALUE;
        while (console.hasNextInt())
        {
            int x = console.nextInt();
            if (x < min) {
                max = min;
                min = x;
            }
            else if (x < max && x > min) {
                max = x;
            }
        }
        System.out.println(max);

    }
}