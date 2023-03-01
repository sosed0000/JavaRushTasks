package com.javarush.task.pro.task03.task0312;

import java.util.Scanner;

/* 
Сравним строки
*/

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();
        //напишите тут ваш код
        if (str1.equals(str2))
        System.out.println("строки одинаковые");
        //напишите тут ваш код
        else
        System.out.println("строки разные");
    }
}
