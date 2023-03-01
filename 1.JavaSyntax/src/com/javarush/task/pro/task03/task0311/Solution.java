package com.javarush.task.pro.task03.task0311;

import java.util.Scanner;

/* 
Высокая точность
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Scanner scanner = new Scanner(System.in);
        double d1 = scanner.nextDouble();
        double d2 = scanner.nextDouble();
        if (Math.abs(d1 - d2) < 0.000001)
        System.out.println("числа равны");
        //напишите тут ваш код
        else
        System.out.println("числа не равны");
    }
}
