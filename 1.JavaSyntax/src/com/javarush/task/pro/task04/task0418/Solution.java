package com.javarush.task.pro.task04.task0418;

import java.util.Scanner;

/* 
Стакан наполовину пуст или наполовину полон?
*/

public class Solution {
    public static void main(String[] args) {
        double glass = 0.5;
        //напишите тут ваш код
        boolean po;
        int res;
        Scanner scanner = new Scanner(System.in);
        po = scanner.nextBoolean();
        res = po ? (int) (Math.ceil(glass)) : (int) (Math.floor(glass));
        System.out.println(res);

    }
}