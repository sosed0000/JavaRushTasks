package com.javarush.task.pro.task02.task0215;

import java.util.Scanner;

/* 
Чтение чисел
*/

public class Solution {

    public static void main(String[] args) {
        //напишите тут ваш код
        Scanner innums = new Scanner(System.in);
        int a, b, c, average;
        a = innums.nextInt();
        b = innums.nextInt();
        c = innums.nextInt();
        average = (a + b + c) / 3;
        System.out.println(average);

    }
}
