package com.javarush.task.pro.task04.task0417;

import java.util.Scanner;

/* 
Скорость ветра
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Scanner scanner = new Scanner(System.in);
        int msSpeed, kmhSpeed;
        msSpeed = scanner.nextInt();
        kmhSpeed = (int) (Math.round(msSpeed * 3.6));
        System.out.println(kmhSpeed);
    }
}