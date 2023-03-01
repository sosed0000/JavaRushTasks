package com.javarush.task.pro.task04.task0416;

import java.util.Scanner;

/* 
Share a Coke
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Scanner scanner = new Scanner(System.in);
        int p, q;
        double res;
        p = scanner.nextInt();
        q = scanner.nextInt();
        res = p * 1.0 / q;
        System.out.println(res);
    }
}