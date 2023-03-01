package com.javarush.task.pro.task04.task0415;

import java.util.Scanner;

/* 
Площадь круга
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        int radius;
        double PI = 3.14;
        int s;
        Scanner scanner = new Scanner(System.in);
        radius = scanner.nextInt();
        if (radius >= 0) {
            s = (int) (PI * radius * radius);
            System.out.println(s);
        }
    }
}