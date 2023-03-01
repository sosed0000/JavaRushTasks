package com.javarush.task.task30.task3010;

import java.math.BigInteger;/*
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        int minRadix = 0;
        for (int i = 36; i > 1; i--) {
            try {
                BigInteger number = new BigInteger(args[0], i);
                minRadix = i;
            } catch (Exception ignored) {}
        }
        if (minRadix == 0)
            System.out.println("incorrect");
        else
            System.out.println(minRadix);
    }
}