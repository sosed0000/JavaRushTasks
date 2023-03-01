package com.javarush.task.pro.task04.task0405;

/* 
Незаполненный прямоугольник
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        int i = 0, j =0;
        while (i < 10) {
            while (j < 20) {
                if (i != 0  && i != 9 && j != 0 && j != 19) System.out.print(" ");
                else System.out.print("Б");
                j++;
            }
            System.out.println();
            j = 0;
            i++;
        }

    }
}