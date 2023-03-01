package com.javarush.task.pro.task05.task0508;

import java.util.Scanner;

/* 
Удаляем одинаковые строки
*/

public class Solution {
    public static String[] strings;

    public static void main(String[] args) {
        //напишите тут ваш код
        Scanner scanner = new Scanner(System.in);
        strings = new String[6];
        for (int i = 0; i < 6; i++) strings[i] = scanner.nextLine();
        String temp;
        for (int i = 0; i < 6; i++) {
            if (strings[i] == null) continue;
            temp = strings[i];
            for (int j = i + 1; j < 6; j++) {
                if (strings[j] == null) continue;
                if (strings[j].equals(temp))
                    strings[i] = strings[j] = null;
            }
        }
        for (int i = 0; i < strings.length; i++) {
            System.out.print(strings[i] + ", ");
        }
    }
}
